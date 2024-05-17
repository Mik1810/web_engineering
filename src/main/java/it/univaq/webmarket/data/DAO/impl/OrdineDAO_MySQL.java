package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.OrdineDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.impl.proxy.OrdineProxy;
import it.univaq.webmarket.framework.data.*;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO_MySQL extends DAO implements OrdineDAO {

    private PreparedStatement sOrdineByID;
    private PreparedStatement iOrdine;
    private PreparedStatement uOrdine;
    private PreparedStatement sOrdini;
    private PreparedStatement dOrdine;
    private PreparedStatement sStoricoByID;

    public OrdineDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sOrdineByID = connection.prepareStatement("SELECT * FROM ordine WHERE ID=?");
            iOrdine = connection.prepareStatement("INSERT INTO ordine(stato_consegna, ID_tecnico_ordini, ID_proposta) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            uOrdine = connection.prepareStatement("UPDATE ordine SET stato_consegna=?,feedback=?, ID_tecnico_ordini=?, ID_proposta=?, version=? WHERE ID=? AND version=?");
            sOrdini = connection.prepareStatement("SELECT ID FROM ordine");
            dOrdine = connection.prepareStatement("DELETE FROM ordine WHERE ID=?");
            sStoricoByID = connection.prepareStatement("SELECT ID_ordine FROM chiude WHERE ID_ordinante=?");
        } catch (SQLException e) {
            throw new DataException("Error initializing webmarket data layer", e);
        }
    }

    @Override
    public void destroy() throws DataException {
        try{
            sOrdineByID.close();
            iOrdine.close();
            uOrdine.close();
            sOrdini.close();
            dOrdine.close();
            sStoricoByID.close();
        } catch (SQLException ex) {
            //
        }
    }

    @Override
    public Ordine createOrdine() {
        return new OrdineProxy(getDataLayer());
    }

    private OrdineProxy createOrdine(ResultSet rs) throws DataException {
        try {
            OrdineProxy o = (OrdineProxy) createOrdine();
            o.setKey(rs.getInt("ID"));
            o.setStatoConsegnaKey(rs.getInt("stato_consegna"));
            o.setFeedbackKey(rs.getInt("feedback"));
            o.setVersion(rs.getLong("version"));
            if(rs.getTimestamp("data_di_consegna") != null) {
                o.setDataConsegna(rs.getTimestamp("data").toLocalDateTime());
            }
            o.setTecnicoOrdiniKey(rs.getInt("ID_tecnico_ordini"));
            o.setPropostaKey(rs.getInt("ID_proposta"));
            return o;
        } catch (SQLException ex) {
            throw new DataException("Unable to create ordine object form ResultSet", ex);
        }
    }

    @Override
    public Ordine getOrdine(int key) throws DataException {
        Ordine o = null;
        if (dataLayer.getCache().has(Ordine.class, key)) {
            o = dataLayer.getCache().get(Ordine.class, key);
        } else {
            try {
                sOrdineByID.setInt(1, key);
                try (ResultSet rs = sOrdineByID.executeQuery()) {
                    if (rs.next()) {
                        o = createOrdine(rs);
                        dataLayer.getCache().add(Ordine.class, o);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Ordine by ID", ex);
            }
        }
        return o;
    }

    @Override
    public List<Ordine> getAllOrdini() throws DataException {
        List<Ordine> result = new ArrayList<>();

        try (ResultSet rs = sOrdini.executeQuery()) {
            while (rs.next()) {
                result.add(getOrdine(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ordine", ex);
        }
        return result;
    }

    @Override
    public void storeOrdine(Ordine ordine) throws DataException {
        try {
            if (ordine.getKey() != null && ordine.getKey() > 0) { //update
                if (ordine instanceof DataItemProxy && !((DataItemProxy) ordine).isModified()) {
                    return;
                }
                /*
                * stato_consegna=?,
                * feedback=?,
                * ID_tecnico_ordini=?,
                * ID_proposta=?,
                * version=?
                * WHERE ID=?
                * AND version=?"
                */

                uOrdine.setInt(1, ordine.getStatoConsegna().getKey());
                if(ordine.getFeedback() == null) {
                    uOrdine.setNull(2, Types.INTEGER);
                } else {
                    uOrdine.setInt(2, ordine.getFeedback().getKey());
                }
                uOrdine.setInt(3, ordine.getTecnicoOrdini().getKey());
                uOrdine.setInt(4, ordine.getProposta().getKey());

                long current_version = ordine.getVersion();
                long next_version = current_version + 1;

                uOrdine.setLong(5, next_version);
                uOrdine.setInt(5, ordine.getKey());
                uOrdine.setLong(6, current_version);

                if (uOrdine.executeUpdate() == 0) {
                    throw new OptimisticLockException(ordine);
                } else {
                    ordine.setVersion(next_version);
                }
            } else { //insert

                /*
                * INSERT INTO ordine(stato_consegna, ID_tecnico_ordini, ID_proposta) VALUES (?, ?, ?)
                */
                iOrdine.setInt(1, ordine.getStatoConsegna().getKey());
                iOrdine.setInt(2, ordine.getTecnicoOrdini().getKey());
                iOrdine.setInt(3, ordine.getProposta().getKey());

                if (iOrdine.executeUpdate() == 1) {
                    try (ResultSet keys = iOrdine.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            ordine.setKey(key);
                            dataLayer.getCache().add(Ordine.class, ordine);
                        }
                    }
                }
            }
            if (ordine instanceof DataItemProxy) {
                ((DataItemProxy) ordine).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store Ordinee", ex);
        }
    }

    @Override
    public List<Ordine> getStoricoByID(int key) throws DataException {
        List<Ordine> result = new ArrayList<>();

        try (ResultSet rs = sStoricoByID.executeQuery()) {
            while (rs.next()) {
                result.add(getOrdine(rs.getInt("ID_ordine")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Storico Ordini", ex);
        }
        return result;
    }
}

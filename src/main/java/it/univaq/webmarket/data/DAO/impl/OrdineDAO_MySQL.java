package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.OrdineDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.proxy.OrdineProxy;
import it.univaq.webmarket.framework.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO_MySQL extends DAO implements OrdineDAO {

    private final Integer offset = 5;

    private PreparedStatement sOrdineByID;
    private PreparedStatement sOrdineInStoricoByID;
    private PreparedStatement iOrdine;
    private PreparedStatement uOrdine;
    private PreparedStatement sOrdiniByOrdinante;
    private PreparedStatement sOrdiniByTecnicoOrdini;
    private PreparedStatement sStoricoByOrdinante;

    public OrdineDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sOrdineByID = connection.prepareStatement("SELECT * FROM ordine WHERE ID=?");
            sOrdineInStoricoByID = connection.prepareStatement("SELECT ID_ordine FROM chiude WHERE ID=?");
            iOrdine = connection.prepareStatement("INSERT INTO ordine(stato_consegna, ID_tecnico_ordini, ID_proposta) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            uOrdine = connection.prepareStatement("UPDATE ordine SET stato_consegna=?,feedback=?, ID_tecnico_ordini=?, ID_proposta=?, version=? WHERE ID=? AND version=?");
            sOrdiniByOrdinante = connection.prepareStatement(
                    "SELECT o2.ID " +
                    "FROM ordinante o " +
                    "JOIN richiesta r ON o.ID = r.ID_ordinante " +
                    "JOIN richiestapresaincarico r2 ON r.ID = r2.ID_richiesta " +
                    "JOIN proposta p ON r2.ID = p.ID_richiesta_presa_in_carico " +
                    "JOIN ordine o2 ON p.ID = o2.ID_proposta " +
                    "WHERE o.ID = ? LIMIT ?, ?");
            sOrdiniByTecnicoOrdini = connection.prepareStatement("SELECT * FROM ordine WHERE ID_tecnico_ordini=? LIMIT ?, ?");
            sStoricoByOrdinante = connection.prepareStatement("SELECT ID_ordine FROM chiude WHERE ID_ordinante=? LIMIT ?, ?");
        } catch (SQLException e) {
            throw new DataException("Error initializing webmarket data layer", e);
        }
    }

    @Override
    public void destroy() throws DataException {
        try{
            sOrdineByID.close();
            sOrdineInStoricoByID.close();
            sOrdiniByTecnicoOrdini.close();
            sOrdiniByOrdinante.close();
            sStoricoByOrdinante.close();
            iOrdine.close();
            uOrdine.close();
        } catch (SQLException ex) {
            throw new DataException("Can't destroy prepared statements", ex);
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
            o.setStatoConsegna(rs.getString("stato_consegna"));
            o.setFeedback(rs.getString("feedback"));
            o.setVersion(rs.getLong("version"));
            o.setDataConsegna(rs.getTimestamp("data_di_consegna") != null ? rs.getTimestamp("data_di_consegna").toLocalDateTime().toLocalDate() : null);
            o.setTecnicoOrdiniKey(rs.getInt("ID_tecnico_ordini"));
            o.setPropostaKey(rs.getInt("ID_proposta"));
            return o;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Ordine object form ResultSet", ex);
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
    public Ordine getOrdineInStorico(int key) throws DataException {
        Ordine o = null;
        try {
            sOrdineInStoricoByID.setInt(1, key);
            try (ResultSet rs = sOrdineInStoricoByID.executeQuery()) {
                if (rs.next()) {
                    o = getOrdine(rs.getInt("ID_ordine"));
                    dataLayer.getCache().add(Ordine.class, o);
                }
            }
            return o;
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ordine from Storico by ID", ex);
        }
    }

    @Override
    public List<Ordine> getStorico(Ordinante ordinante, Integer page) throws DataException {
        List<Ordine> ordini = new ArrayList<>();
        try {
            sStoricoByOrdinante.setInt(1, ordinante.getKey());
            sStoricoByOrdinante.setInt(2, page * offset);
            sStoricoByOrdinante.setInt(3, offset);
            try (ResultSet rs = sStoricoByOrdinante.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_ordine");
                    ordini.add(getOrdine(id));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load storico Ordini by Ordinante", ex);
        }
        return ordini;
    }

    @Override
    public List<Ordine> getAllOrdiniByOrdinante(Ordinante ordinante, Integer page) throws DataException {
        List<Ordine> ordini = new ArrayList<>();
        try {
            sOrdiniByOrdinante.setInt(1, ordinante.getKey());
            sOrdiniByOrdinante.setInt(2, page * offset);
            sOrdiniByOrdinante.setInt(3, offset);
            try (ResultSet rs = sOrdiniByOrdinante.executeQuery()) {
                while (rs.next()) {
                    ordini.add(getOrdine(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ordini by Ordinante", ex);
        }
        return ordini;
    }

    @Override
    public List<Ordine> getAllOrdiniByTecnicoOrdine(TecnicoOrdini tecnicoOrdini, Integer page) throws DataException {
        List<Ordine> ordini = new ArrayList<>();
        try {
            sOrdiniByTecnicoOrdini.setInt(1, tecnicoOrdini.getKey());
            sOrdiniByTecnicoOrdini.setInt(2, page * offset);
            sOrdiniByTecnicoOrdini.setInt(3, offset);
            try (ResultSet rs = sOrdiniByTecnicoOrdini.executeQuery()) {
                while (rs.next()) {
                    ordini.add(getOrdine(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ordini by TecnicoOrdini", ex);
        }
        return ordini;
    }

    @Override
    public void storeOrdine(Ordine ordine) throws DataException {
        try {
            if (ordine.getKey() != null && ordine.getKey() > 0) { //update
                if (ordine instanceof DataItemProxy && !((DataItemProxy) ordine).isModified()) {
                    return;
                }
                /*
                * stato_consegna=1,
                * feedback=2,
                * ID_tecnico_ordini=3,
                * ID_proposta=4,
                * version=5
                * WHERE ID=6
                * AND version=7"
                */

                // Per la data di consegna ci pensa il trigger nel db ad inserirla quando vede
                // che lo stato di consegna è "Consegnato"

                uOrdine.setString(1, ordine.getStatoConsegna());
                if (ordine.getFeedback() == null) {
                    uOrdine.setNull(2, Types.VARCHAR);
                } else {
                    uOrdine.setString(2, ordine.getFeedback());
                }
                uOrdine.setInt(3, ordine.getTecnicoOrdini().getKey());
                uOrdine.setInt(4, ordine.getProposta().getKey());

                long current_version = ordine.getVersion();
                long next_version = current_version + 1;

                uOrdine.setLong(5, next_version);
                uOrdine.setInt(6, ordine.getKey());
                uOrdine.setLong(7, current_version);

                if (uOrdine.executeUpdate() == 0) {
                    throw new OptimisticLockException(ordine);
                } else {
                    ordine.setVersion(next_version);
                }
            } else { //insert

                /*
                * INSERT INTO ordine(stato_consegna, ID_tecnico_ordini, ID_proposta) VALUES (?, ?, ?)
                */
                /*
                Non inserisco un feedback perchè di base un utente non può inserirlo se
                lo stato consegna non è "Consegnato", non inserisco nemmeno la data di
                consegna dal momento che appena inserisco un ordine non può essere con
                stato "Consegnato"
                   */
                iOrdine.setString(1, ordine.getStatoConsegna());
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
            throw new DataException("Unable to store Ordine", ex);
        }
    }
}

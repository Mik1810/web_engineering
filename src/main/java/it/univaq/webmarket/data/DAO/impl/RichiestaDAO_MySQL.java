package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.RichiestaDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaProxy;
import it.univaq.webmarket.framework.data.*;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RichiestaDAO_MySQL extends DAO implements RichiestaDAO {

    private PreparedStatement sRichiestaByID;
    private PreparedStatement iRichiesta;
    private PreparedStatement uRichiesta;
    private PreparedStatement dRichiesta;
    private PreparedStatement sRichiesteByIDOrdinante;
    private PreparedStatement sRichiesteByIDOrdinantePage;

    public RichiestaDAO_MySQL(DataLayer d) {
        super(d);
    }

    private final Integer offset = 5;

    @Override
    public void init() throws DataException {
        try {
            super.init();

            iRichiesta = connection.prepareStatement("INSERT INTO richiesta (codice_richiesta, note, data, ID_ordinante) VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            uRichiesta = connection.prepareStatement("UPDATE richiesta SET codice_richiesta=?, note=?, data=?, ID_ordinante=?, version=? WHERE ID=? AND version=?");
            dRichiesta = connection.prepareStatement("DELETE FROM richiesta WHERE ID=?");
            sRichiestaByID = connection.prepareStatement("SELECT * FROM richiesta WHERE ID=? ORDER BY data DESC");
            sRichiesteByIDOrdinante = connection.prepareStatement("SELECT ID FROM richiesta WHERE ID_ordinante=?");
            sRichiesteByIDOrdinantePage = connection.prepareStatement("SELECT ID FROM richiesta WHERE ID_ordinante=? LIMIT ?,?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
         try {
             iRichiesta.close();
             dRichiesta.close();
             sRichiestaByID.close();
             sRichiesteByIDOrdinante.close();
             sRichiesteByIDOrdinantePage.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public Richiesta createRichiesta() {
        return new RichiestaProxy(getDataLayer());
    }

    private RichiestaProxy createRichiestaAcquisto(ResultSet rs) throws DataException {
        RichiestaProxy ra = (RichiestaProxy) createRichiesta();
        try {
            ra.setKey(rs.getInt("ID"));
            ra.setCodiceRichiesta(rs.getString("codice_richiesta"));
            ra.setNote(rs.getString("note"));
            ra.setData(rs.getTimestamp("data").toLocalDateTime().toLocalDate());
            ra.setOrdinante_key(rs.getInt("ID_ordinante"));
            ra.setVersion(rs.getLong("version"));
        } catch (SQLException ex) {
            throw new DataException("Unable to create Richiesta from ResultSet", ex);
        }
        return ra;
    }

    @Override
    public Richiesta getRichiesta(int richiesta_key) throws DataException {
        Richiesta ra = null;
        //prima vediamo se l'oggetto è già stato caricato
        if (dataLayer.getCache().has(Richiesta.class, richiesta_key)) {
            ra = dataLayer.getCache().get(Richiesta.class, richiesta_key);
        } else {
            try {
                sRichiestaByID.setInt(1, richiesta_key);
                try (ResultSet rs = sRichiestaByID.executeQuery()) {
                    if (rs.next()) {
                        ra = createRichiestaAcquisto(rs);
                        //e lo mettiamo anche nella cache
                        dataLayer.getCache().add(Richiesta.class, ra);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Richiesta by ID", ex);
            }
        }
        return ra;
    }

    @Override
    public List<Richiesta> getRichiesteByOrdinante(Ordinante ordinante) throws DataException {
        List<Richiesta> result = new ArrayList<>();
        try {
            sRichiesteByIDOrdinante.setInt(1, ordinante.getKey());
            try (ResultSet rs = sRichiesteByIDOrdinante.executeQuery()){
                while (rs.next()){
                    result.add(getRichiesta(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all Richieste from Ordinante", ex);
        }
    }

    @Override
    public List<Richiesta> getRichiesteByOrdinante(Ordinante ordinante, Integer page) throws DataException {
        List<Richiesta> result = new ArrayList<>();
        try {
            sRichiesteByIDOrdinantePage.setInt(1, ordinante.getKey());
            sRichiesteByIDOrdinantePage.setInt(2, page * offset);
            sRichiesteByIDOrdinantePage.setInt(3, offset);
            try (ResultSet rs = sRichiesteByIDOrdinantePage.executeQuery()){
                while (rs.next()){
                    result.add(getRichiesta(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all Richieste paginate from Ordinante", ex);
        }
    }

    @Override
    public void storeRichiesta(Richiesta richiesta) throws DataException {
        //INSERT INTO richiesta (codice_richiesta, note, data, ID_ordinante)
        try {
            if (richiesta.getKey() != null && richiesta.getKey() > 0) { //update
                if (richiesta instanceof DataItemProxy && !((DataItemProxy) richiesta).isModified()) {
                    return;
                }
                /*
                codice_richiesta=1,
                note=2,
                data=3,
                ID_ordinante=4,
                version=5
                WHERE ID=6
                AND version=7
                */
                uRichiesta.setString(1, richiesta.getCodiceRichiesta());
                uRichiesta.setString(2, richiesta.getNote());
                uRichiesta.setTimestamp(3, Timestamp.valueOf(richiesta.getData().atStartOfDay()));
                uRichiesta.setInt(4, richiesta.getOrdinante().getKey());

                long current_version = richiesta.getVersion();
                long next_version = current_version + 1;

                uRichiesta.setLong(5, next_version);
                uRichiesta.setInt(6, richiesta.getKey());
                uRichiesta.setLong(7, current_version);

                if (uRichiesta.executeUpdate() == 0) {
                    throw new OptimisticLockException(richiesta);
                } else {
                    richiesta.setVersion(next_version);
                }

            } else { //INSERT
                //TODO: verificare che il codice richiesta non esista già nel DB
                iRichiesta.setString(1, getRandomCodiceRichiesta(10));
                if (richiesta.getNote() != null) {
                    iRichiesta.setString(2, richiesta.getNote());
                } else {
                    iRichiesta.setNull(2, Types.VARCHAR);
                }
                iRichiesta.setTimestamp(3, Timestamp.valueOf(richiesta.getData().atStartOfDay()));
                iRichiesta.setInt(4, richiesta.getOrdinante().getKey());

                if (iRichiesta.executeUpdate() == 1) {
                    try (ResultSet keys = iRichiesta.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            richiesta.setKey(key);
                            dataLayer.getCache().add(Richiesta.class, richiesta);
                        }
                    }
                }
            }
            if (richiesta instanceof DataItemProxy) {
                ((DataItemProxy) richiesta).setModified(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store Richiesta", ex);
        }
    }

    @Override
    public void deleteRichiesta(Richiesta richiesta) throws DataException {
        try {

            //Lo cancello prima dalla cache
            dataLayer.getCache().delete(Richiesta.class, richiesta);

            dRichiesta.setInt(1, richiesta.getKey());
            dRichiesta.executeUpdate();

        } catch(SQLException e) {
            throw new DataException("Unable to delete Richiesta", e);
        }
    }



    private String getRandomCodiceRichiesta(int n) {
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < n; i++) {
            code.append(r.nextInt(10));
        }
        return code.toString();
    }
}

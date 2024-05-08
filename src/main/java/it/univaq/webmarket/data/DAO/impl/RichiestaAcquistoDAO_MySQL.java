package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaAcquistoProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RichiestaAcquistoDAO_MySQL extends DAO implements RichiestaAcquistoDAO {

    private PreparedStatement sRichiestaByID;
    private PreparedStatement sAllRichieste;

    public RichiestaAcquistoDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();


            sAllRichieste = connection.prepareStatement("SELECT * FROM RichiestaAcquisto");
            sRichiestaByID = connection.prepareStatement("SELECT * FROM RichiestaAcquisto WHERE ID=?");

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
         try {

           sAllRichieste.close();
            sRichiestaByID.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public RichiestaAcquisto createRichiestaAcquisto() {
        return new RichiestaAcquistoProxy(getDataLayer());
    }

    private RichiestaAcquistoProxy createRichiestaAcquisto(ResultSet rs) throws DataException {
        System.out.println("Parsando richieste di acquisto...");
        RichiestaAcquistoProxy ra = (RichiestaAcquistoProxy) createRichiestaAcquisto();
        try {
            ra.setKey(rs.getInt("ID"));
            ra.setCodiceRichiesta(rs.getString("codice_richiesta"));
            ra.setNote(rs.getString("note"));
            ra.setDataEOra(rs.getTimestamp("data").toLocalDateTime());
            //ra.setOrdinante_key(rs.getInt("ID_ordinante"));
            //TODO: Che ci dobbiamo fa co sto version?
            //a.setVersion(rs.getLong("version"));
        } catch (SQLException ex) {
            throw new DataException("Unable to create article object form ResultSet", ex);
        }
        return ra;
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto(int richiesta_key) throws DataException {
        System.out.println("Richiedendo una richiesta di acquisto con ID...");
        RichiestaAcquisto ra = null;
        //prima vediamo se l'oggetto è già stato caricato
        //first look for this object in the cache
        if (dataLayer.getCache().has(RichiestaAcquisto.class, richiesta_key)) {
            ra = dataLayer.getCache().get(RichiestaAcquisto.class, richiesta_key);
        } else {
            //altrimenti lo carichiamo dal database
            //otherwise load it from database
            try {
                sRichiestaByID.setInt(1, richiesta_key);
                try (ResultSet rs = sRichiestaByID.executeQuery()) {
                    if (rs.next()) {
                        ra = createRichiestaAcquisto(rs);
                        //e lo mettiamo anche nella cache
                        //and put it also in the cache
                        dataLayer.getCache().add(RichiestaAcquisto.class, ra);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load request by ID", ex);
            }
        }
        return ra;
    }

    @Override
    public List<RichiestaAcquisto> getAllRichiesteAcquisto() throws DataException {
        System.out.println("Richiedendo tutte le richieste di acquisto...");
        List<RichiestaAcquisto> result = new ArrayList<>();

        try (ResultSet rs = sAllRichieste.executeQuery()) {
            while (rs.next()) {
                System.out.printf("ID: %d\n", rs.getInt("ID"));
                result.add(getRichiestaAcquisto(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load articles", ex);
        }
        return result;
    }
}

package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.RichiestaDAO;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RichiestaDAO_MySQL extends DAO implements RichiestaDAO {

    private PreparedStatement sRichiestaByID;
    private PreparedStatement sAllRichieste;

    public RichiestaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();


            sAllRichieste = connection.prepareStatement("SELECT ID FROM RichiestaAcquisto");
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
    public Richiesta createRichiestaAcquisto() {
        return new RichiestaProxy(getDataLayer());
    }

    private RichiestaProxy createRichiestaAcquisto(ResultSet rs) throws DataException {
        RichiestaProxy ra = (RichiestaProxy) createRichiestaAcquisto();
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
    public Richiesta getRichiestaAcquisto(int richiesta_key) throws DataException {
        Richiesta ra = null;
        //prima vediamo se l'oggetto è già stato caricato
        if (dataLayer.getCache().has(Richiesta.class, richiesta_key)) {
            Logger.getLogger("RichiestaAcquistoDAO_MySQL")
                    .log(Level.INFO, "Cache: Hit {0}", new Object[]{this.getClass()});
            ra = dataLayer.getCache().get(Richiesta.class, richiesta_key);
        } else {
            Logger.getLogger("RichiestaAcquistoDAO_MySQL")
                    .log(Level.INFO, "Cache: Miss {0}", new Object[]{this.getClass()});
            //altrimenti lo carichiamo dal database
            //otherwise load it from database
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
                throw new DataException("Unable to load request by ID", ex);
            }
        }
        return ra;
    }

    @Override
    public void storeRichiestaAcquisto(Richiesta richiesta) throws DataException {
        //TODO: Implementare storeRichiestaAcquisto
    }

    @Override
    public void deleteRichiestaAcquisto(Richiesta richiesta) throws DataException {
        //TODO
    }
}

package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.RichiestaConCaratteristicaDAO;
import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.data.model.impl.RichiestaConCaratteristicheImpl;
import it.univaq.webmarket.data.model.impl.proxy.CategoriaPadreProxy;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaConCaratteristicaProxy;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RichiestaConCaratteristicaDAO_MySQL extends DAO implements RichiestaConCaratteristicaDAO {

    private Integer offset = 5;

    // Per la select tramite ID
    private PreparedStatement sRichiestaByID;
    private PreparedStatement sCaratteristicheByRichiestaID;


    private PreparedStatement iRichiestaConCaratteristica;
    private PreparedStatement uRichiestaConCaratteristica;
    private PreparedStatement sRichiesteConCaratteristica;
    private PreparedStatement dRichiestaConCaratteristica;

    public RichiestaConCaratteristicaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        super.init();
        try {
            sCaratteristicheByRichiestaID = connection.prepareStatement(
                    "SELECT c.ID_caratteristica, c.valore " +
                            "FROM composta AS c " +
                            "WHERE c.ID_richiesta = 1;");
            /*
            iRichiestaConCaratteristica = connection.prepareStatement("INSERT INTO richiesta_con_caratteristica(stato_consegna, ID_tecnico_ordini, ID_proposta) VALUES (?, ?, ?)");
            uRichiestaConCaratteristica = connection.prepareStatement("UPDATE richiesta_con_caratteristica SET stato_consegna=?,feedback=?, ID_tecnico_ordini=?, ID_proposta=?, version=? WHERE ID=? AND version=?");
            sRichiesteConCaratteristica = connection.prepareStatement("SELECT ID FROM richiesta_con_caratteristica LIMIT ?, ?");
            dRichiestaConCaratteristica = connection.prepareStatement("DELETE FROM richiesta_con_caratteristica WHERE ID=?");
             */
        } catch (Exception e) {
            throw new DataException("Error initializing webmarket data layer", e);
        }
    }

    @Override
    public void destroy() throws DataException {
        try{
            sRichiestaByID.close();
            sCaratteristicheByRichiestaID.close();
            /*
            iRichiestaConCaratteristica.close();
            uRichiestaConCaratteristica.close();
            sRichiesteConCaratteristica.close();
            dRichiestaConCaratteristica.close();
            */
        } catch (SQLException e) {
            //
        }
        super.destroy();
    }

    @Override
    public RichiestaConCaratteristiche createRichiestaConCaratteristica() {
        return new RichiestaConCaratteristicaProxy(getDataLayer());
    }

    @Override
    public RichiestaConCaratteristiche getRichiestaConCaratteristica(int richiestaConCaratteristiche_key) throws DataException {
        RichiestaConCaratteristiche rcc = null;
        if (dataLayer.getCache().has(RichiestaConCaratteristiche.class, richiestaConCaratteristiche_key)) {
            rcc = dataLayer.getCache().get(RichiestaConCaratteristiche.class, richiestaConCaratteristiche_key);
        } else {
            try {

                WebmarketDataLayer dl = (WebmarketDataLayer) getDataLayer();
                rcc = createRichiestaConCaratteristica();

                Richiesta richiesta = dl.getRichiestaDAO().getRichiestaAcquisto(richiestaConCaratteristiche_key);
                Map<Caratteristica, String> caratteristiche = new HashMap<>();

                try (ResultSet rs = sCaratteristicheByRichiestaID.executeQuery()) {
                    while (rs.next()) {
                        Caratteristica caratteristica = dl.getCaratteristicaDAO().getCaratteristica(rs.getInt("ID_caratteristica"));
                        caratteristiche.put(caratteristica, rs.getString("valore"));
                    }
                    rcc.setRichiesta(richiesta);
                    rcc.setCaratteristiche(caratteristiche);
                    dataLayer.getCache().add(RichiestaConCaratteristiche.class, rcc);
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load RichiestaConCaratteristica by ID", ex);
            }
        }
        return rcc;
    }

    @Override
    public List<RichiestaConCaratteristiche> getAllRichiesteConCaratteristicaByOrdinante(Integer page) throws DataException {
        return List.of();
    }

    @Override
    public void storeRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException {

    }

    @Override
    public void deleteRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException {

    }
}

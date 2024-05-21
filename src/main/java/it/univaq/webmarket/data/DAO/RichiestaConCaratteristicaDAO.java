package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;
import java.util.Map;

public interface RichiestaConCaratteristicaDAO {

    RichiestaConCaratteristiche createRichiestaConCaratteristica();

    RichiestaConCaratteristiche getRichiestaConCaratteristica(int richiesta_key) throws DataException;

    List<RichiestaConCaratteristiche> getRichiesteConCaratteristica() throws DataException;

    void storeRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException;

    void deleteRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException;

}

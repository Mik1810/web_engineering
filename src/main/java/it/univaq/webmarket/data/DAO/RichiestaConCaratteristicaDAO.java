package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaConCaratteristicaDAO {

    RichiestaConCaratteristiche createRichiestaConCaratteristica() throws DataException;

    // La RichiestaConCaratteristiche presenta la stessa chiave della Richiesta a cui è associata
    RichiestaConCaratteristiche getRichiestaConCaratteristica(int richiestaConCaratteristiche_key) throws DataException;

    List<RichiestaConCaratteristiche> getAllRichiesteConCaratteristicaByOrdinante(Integer page) throws DataException;

    void storeRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException;

    void deleteRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche) throws DataException;

}

package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;

import java.util.Map;

public interface RichiestaConCaratteristicaDAO {

    RichiestaConCaratteristiche createRichiestaConCaratteristica();

    // avevamo detto che la mappa sarebbe stata creata dal DAO con i dati dal DB e poi propagata nel resto della struttura
    Map<Caratteristica, String> getRichiestaConCaratteristica(int richiesta_key);

    void storeRichiestaConCaratteristica(RichiestaConCaratteristiche richiestaConCaratteristiche);

    void deleteRichiestaConCaratteristica(int richiesta_key);

}

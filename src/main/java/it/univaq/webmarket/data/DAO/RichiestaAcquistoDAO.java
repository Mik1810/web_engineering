package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaAcquistoDAO {

    Richiesta createRichiestaAcquisto();

    Richiesta getRichiestaAcquisto(int richiesta_key) throws DataException;

    List<Richiesta> getAllRichiesteAcquisto() throws DataException;

}

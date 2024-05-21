package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaDAO {

    Richiesta createRichiestaAcquisto();

    Richiesta getRichiestaAcquisto(int richiesta_key) throws DataException;

    void storeRichiestaAcquisto(Richiesta richiesta) throws DataException;

    void deleteRichiestaAcquisto(Richiesta richiesta) throws DataException;
}

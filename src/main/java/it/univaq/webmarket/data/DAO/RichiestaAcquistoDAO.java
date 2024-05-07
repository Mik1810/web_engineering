package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaAcquistoDAO {

    RichiestaAcquisto createRichiestaAcquisto();

    RichiestaAcquisto getRichiestaAcquisto(int richiesta_key) throws DataException;

    List<RichiestaAcquisto> getAllRichiesteAcquisto() throws DataException;


}

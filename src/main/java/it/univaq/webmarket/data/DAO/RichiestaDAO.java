package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaDAO {

    Richiesta createRichiesta();

    Richiesta getRichiesta(int richiesta_key) throws DataException;

    List<Richiesta> getRichiesteByOrdinante(Ordinante ordinante) throws DataException;

    void storeRichiesta(Richiesta richiesta) throws DataException;

    void deleteRichiesta(Richiesta richiesta) throws DataException;

    List<CaratteristicaConValore> getCaratteristicheConValore(Richiesta richiesta) throws DataException;
}

package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface OrdineDAO {

    Ordine createOrdine();

    Ordine getOrdine(int key) throws DataException;

    // Ritorna una lista di ordini chiusi, cioè con stato ordine "Consegnato" associati ad un ordinante
    List<Ordine> getStorico(Ordinante ordinante, Integer page) throws DataException;

    // Ritorna una lista di ordini associati ad un ordinante
    List<Ordine> getAllOrdiniByOrdinante(Ordinante ordinante, Integer page) throws DataException;

    // Ritorna una lista di ordini gestiti da uno specifico tecnico degli ordini
    List<Ordine> getAllOrdiniByTecnicoOrdine(TecnicoOrdini tecnicoOrdini, Integer page) throws DataException;

    void storeOrdine(Ordine ordine) throws DataException;
}

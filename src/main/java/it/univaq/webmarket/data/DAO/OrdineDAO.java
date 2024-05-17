package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface OrdineDAO {

    Ordine createOrdine();

    Ordine getOrdine(int key) throws DataException;

    List<Ordine> getAllOrdini() throws DataException;

    void storeOrdine(Ordine ordine) throws DataException;

    List<Ordine> getStoricoByID(int key) throws DataException;
}

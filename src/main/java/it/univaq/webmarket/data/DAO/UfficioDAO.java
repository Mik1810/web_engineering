package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;


public interface UfficioDAO {

    Ufficio createUfficio();

    Ufficio getUfficio(int ufficio_key) throws DataException;

    void storeUfficio(Ufficio ufficio) throws DataException;

    void deleteUfficio(Ufficio ufficio) throws DataException;

    List<Ufficio> getAllUffici(Integer page) throws DataException;
}

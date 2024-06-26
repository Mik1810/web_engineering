package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;


public interface UfficioDAO {

    /**
     * Crea un oggetto di tipo Ufficio.
     *
     * @return un oggetto di tipo Ufficio.
     */
    Ufficio createUfficio();

    /**
     * Restituisce l'oggetto Ufficio con l'id passato come parametro
     *
     * @param ufficio_key id dell'Ufficio
     * @return l'oggetto Ufficio con l'id passato come parametro
     */
    Ufficio getUfficio(int ufficio_key) throws DataException;

    /**
     * Restituisce tutte gli Uffici paginati presenti nel database
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti verranno restituiti
     *               a partire da page*offset fino a page*offset+offset
     * @return una lista di oggetti di tipo Ufficio
     */
    List<Ufficio> getAllUffici(Integer page) throws DataException;

    /**
     * Restituisce tutte gli Uffici presenti nel database
     *
     * @return una lista di oggetti di tipo Ufficio
     */
    List<Ufficio> getAllUffici() throws DataException;

    /**
     * Salva nel database un nuovo Ufficio o aggiorna quello esistente
     *
     * @param ufficio l'oggetto di tipo Ufficio da salvare
     */
    void storeUfficio(Ufficio ufficio) throws DataException;

    /**
     * Cancella dal database un Ufficio
     *
     * @param ufficio l'oggetto di tipo Ufficio da cancellare
     */
    void deleteUfficio(Ufficio ufficio) throws DataException;
}

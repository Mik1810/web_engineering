package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface TecnicoOrdiniDAO {

    /**
     * Crea un oggetto di tipo TecnicoOrdini.
     *
     * @return un oggetto di tipo TecnicoOrdini.
     */
    TecnicoOrdini createTecnicoOrdini();

    /**
     * Restituisce l'oggetto TecnicoOrdini con l'id passato come parametro
     *
     * @param tecnicoOrdini_key id del TecnicoOrdini
     * @return l'oggetto TecnicoOrdini con l'id passato come parametro
     */
    TecnicoOrdini getTecnicoOrdini(int tecnicoOrdini_key) throws DataException;

    /**
     * Restituisce la lista dei TecnicoOrdini paginati presenti nel database
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di TecnicoOrdini
     */
    List<TecnicoOrdini> getAllTecnicoOrdini(Integer page) throws DataException;

    /**
     * Restituisce l'oggetto TecnicoOrdini con l'email passata come parametro
     *
     * @param email email del TecnicoOrdini
     * @return l'oggetto TecnicoOrdini con l'email passata come parametro
     */
    TecnicoOrdini getTecnicoOrdiniByEmail(String email) throws DataException;

    /**
     * Salva nel database un nuovo TecnicoOrdini o aggiorna quello esistente
     *
     * @param tecnicoOrdini l'oggetto di tipo TecnicoOrdini da salvare
     */
    void storeTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException;

    /**
     * Cancella dal database un TecnicoOrdini
     *
     * @param tecnicoOrdini l'oggetto di tipo TecnicoOrdini da cancellare
     */
    void deleteTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException;
}

package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface TecnicoPreventiviDAO {

    /**
     * Crea un oggetto di tipo TecnicoPreventivi.
     *
     * @return un oggetto di tipo TecnicoPreventivi.
     */
    TecnicoPreventivi createTecnicoPreventivi();

    /**
     * Restituisce l'oggetto TecnicoPreventivi con l'id passato come parametro
     *
     * @param tecnicoPreventivi_key id del TecnicoPreventivi
     * @return l'oggetto TecnicoPreventivi con l'id passato come parametro
     */
    TecnicoPreventivi getTecnicoPreventivi(int tecnicoPreventivi_key) throws DataException;

    /**
     * Restituisce la lista dei TecnicoPreventivi paginati presenti nel database
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di TecnicoPreventivi
     */
    List<TecnicoPreventivi> getAllTecnicoPreventivi(Integer page) throws DataException;

    /**
     * Restituisce l'oggetto TecnicoPreventivi con l'email passata come parametro
     *
     * @param email email del TecnicoPreventivi
     * @return l'oggetto TecnicoPreventivi con l'email passata come parametro
     */
    TecnicoPreventivi getTecnicoPreventiviByEmail(String email) throws DataException;

    /**
     * Salva nel database un nuovo TecnicoPreventivi o aggiorna quello esistente
     *
     * @param tecnicoPreventivi l'oggetto di tipo TecnicoPreventivi da salvare
     */
    void storeTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException;

    /**
     * Cancella dal database un TecnicoPreventivi
     *
     * @param tecnicoPreventivi l'oggetto di tipo TecnicoPreventivi da cancellare
     */
    void deleteTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException;
}


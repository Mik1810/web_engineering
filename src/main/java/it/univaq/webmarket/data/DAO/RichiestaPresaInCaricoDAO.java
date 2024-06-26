package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaPresaInCaricoDAO {

    /**
     * Crea un oggetto di tipo RichiestaPresaInCarico.
     *
     * @return un oggetto di tipo RichiestaPresaInCarico.
     */
    RichiestaPresaInCarico createRichiestaPresaInCarico();

    /**
     * Restituisce l'oggetto RichiestaPresaInCarico con l'id passato come parametro
     *
     * @param key id della RichiestaPresaInCarico
     * @return l'oggetto RichiestaPresaInCarico con l'id passato come parametro
     */
    RichiestaPresaInCarico getRichiestaPresaInCarico(Integer key) throws DataException;

    /**
     * Restituisce la lista dei RichiestaPresaInCarico paginate gestite da un TecnicoPreventivi
     *
     * @param tecnicoPreventivi l'oggetto di tipo TecnicoPreventivi
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di RichiestaPresaInCarico gestite da un TecnicoPreventivi
     */
    List<RichiestaPresaInCarico> getAllRichiestePresaInCaricoByTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi, Integer page) throws DataException;

    /**
     * Salva nel database una nuova RichiestaPresaInCarico o aggiorna quella esistente
     *
     * @param richiestaPresaInCarico l'oggetto di tipo RichiestaPresaInCarico da salvare
     */
    void storeRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException;

    /**
     * Cancella dal database una RichiestaPresaInCarico
     *
     * @param richiestaPresaInCarico l'oggetto di tipo RichiestaPresaInCarico da cancellare
     */
    void deleteRichiestaPresaInCarico(RichiestaPresaInCarico richiestaPresaInCarico) throws DataException;

}

package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface RichiestaDAO {

    /**
     * Crea un oggetto di tipo Richiesta.
     *
     * @return un oggetto di tipo Richiesta.
     */
    Richiesta createRichiesta();

    /**
     * Restituisce l'oggetto Richiesta con l'id passato come parametro
     *
     * @param richiesta_key id dell'Richiesta
     * @return l'oggetto Richiesta con l'id passato come parametro
     */
    Richiesta getRichiesta(int richiesta_key) throws DataException;

    /**
     * Restituisce la lista delle Richieste paginate create da un Ordinante
     *
     * @param ordinante l'oggetto di tipo Ordinante di cui si vogliono prendere le Richieste
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Richieste
     */
    List<Richiesta> getRichiesteByOrdinante(Ordinante ordinante, Integer page) throws DataException;

    /**
     * Restituisce la lista delle Richieste paginate non gestite da nessun TecnicoPreventivi, cioè
     * quelle Richieste per la quale non esiste nessuna RichiestaPresaInCarico associata
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Richieste non gestite
     */
    List<Richiesta> getRichiesteNonGestite(Integer page) throws DataException;

    /**
     * Salva nel database una nuovo Richiesta o aggiorna quella esistente
     *
     * @param richiesta l'oggetto di tipo Ordine da salvare
     */
    void storeRichiesta(Richiesta richiesta) throws DataException;

    /**
     * Cancella dal database una Richiesta
     *
     * @param richiesta l'oggetto di tipo Richiesta da cancellare
     */
    void deleteRichiesta(Richiesta richiesta) throws DataException;

}

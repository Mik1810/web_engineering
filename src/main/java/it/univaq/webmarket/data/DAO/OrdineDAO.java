package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface OrdineDAO {

    /**
     * Crea un oggetto di tipo Ordine.
     *
     * @return un oggetto di tipo Ordine.
     */
    Ordine createOrdine();

    /**
     * Restituisce l'oggetto Ordine con l'id passato come parametro
     *
     * @param key id dell'Ordine
     * @return l'oggetto Ordine con l'id passato come parametro
     */
    Ordine getOrdine(int key) throws DataException;

    /**
     * Restituisce l'oggetto Ordine con l'id passato come parametro all'interno dello storico
     * di un Ordinante
     *
     * @param key id dell'Ordine
     * @return l'oggetto Ordine con l'id passato come parametro
     */
    Ordine getOrdineInStorico(int key) throws DataException;

    /**
     * Restituisce la lista degli Ordini paginati  presenti all'interno dello storico di un Ordinante
     *
     * @param ordinante l'oggetto di tipo Ordinante
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Ordini chiusi, cioè con stato ordine "Consegnato" associati ad un ordinante
     */
    List<Ordine> getStorico(Ordinante ordinante, Integer page) throws DataException;

    /**
     * Restituisce una lisya di Ordini paginati effettuati da un Ordinante
     *
     * @param ordinante l'oggetto di tipo Ordinante di cui si vuole conoscere quali Ordini ha attivi
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Ordini effettuati da un Ordinante
     */
    List<Ordine> getAllOrdiniByOrdinante(Ordinante ordinante, Integer page) throws DataException;

    /**
     * Ritorna una lista di Ordini paginati gestiti da uno specifico tecnico degli ordini
     *
     * @param tecnicoOrdini il TecnicoOrdini di cui si vogliono conoscere gli Ordini gestiti
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti
     *             verranno restituiti da page*offset fino a page*offset+offset
     * @return una lista di Ordini creati da un Ordinante
     */
    List<Ordine> getAllOrdiniByTecnicoOrdine(TecnicoOrdini tecnicoOrdini, Integer page) throws DataException;

    /**
     * Salva nel database una nuovo Ordine o aggiorna quella esistente
     *
     * @param ordine l'oggetto di tipo Ordine da salvare
     */
    void storeOrdine(Ordine ordine) throws DataException;
}

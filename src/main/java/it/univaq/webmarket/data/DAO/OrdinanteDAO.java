package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface OrdinanteDAO {

    /**
     * Crea un oggetto di tipo Ordinante.
     *
     * @return un oggetto di tipo Ordinante.
     */
    Ordinante createOrdinante();

    /**
     * Restituisce l'oggetto Ordinante con l'id passato come parametro
     *
     * @param ordinante_key id della Ordinante
     * @return l'oggetto Ordinante con l'id passato come parametro
     */
    Ordinante getOrdinante(int ordinante_key) throws DataException;

    /**
     * Restituisce tutte le Ordinanti presenti nel database paginate con un certo offset
     * (l'offset è dichiarato nell implementazione di questa interfaccia)
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti verranno restituiti
     *               a partire da page*offset fino a page*offset+offset
     * @return una lista di oggetti di tipo Ordinante
     */
    List<Ordinante> getAllOrdinanti(Integer page) throws DataException;

    /**
     * Restituisce l'oggetto Ordinante con l'email passata come parametro
     *
     * @param email email dell'Ordinante
     * @return l'oggetto Ordinante con l'email passata come parametro
     */
    Ordinante getOrdinanteByEmail(String email) throws DataException;

    /**
     * Salva nel database un nuovo Ordinante o aggiorna quello esistente
     *
     * @param ordinante l'oggetto di tipo Ordinante da salvare
     */
    void storeOrdinante(Ordinante ordinante) throws DataException;

    /**
     * Cancella dal database un Ordinante
     *
     * @param ordinante l'oggetto di tipo Ordinante da cancellare
     */
    void deleteOrdinante(Ordinante ordinante) throws DataException;
}

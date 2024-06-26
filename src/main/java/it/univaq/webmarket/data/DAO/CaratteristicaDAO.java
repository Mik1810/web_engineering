package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface CaratteristicaDAO {

    /**
     * Crea un oggetto di tipo Caratteristica
     *
     * @return un oggetto di tipo Caratteristica.
     */
    Caratteristica createCaratteristica();

    /**
     * Restituisce l'oggetto Caratteristica con l'id passato come parametro
     *
     * @param key id della caratteristica
     * @return l'oggetto Caratteristica con l'id passato come parametro
     */
    Caratteristica getCaratteristica(int key) throws DataException;

    /**
     * Restituisce tutte le caratteristiche presenti nel database paginate con un certo offset
     * (l'offset è dichiarato nell implementazione di questa interfaccia)
     *
     * @param page indica il valore della paginazione degli oggetti, questo significa che gli oggetti verranno restituiti
     *             a partire da page*offset fino a page*offset+offset
     * @return una lista di oggetti di tipo Caratteristica
     */
    List<Caratteristica> getAllCaratteristiche(Integer page) throws DataException;

    /**
     * Restituisce tutte le caratteristiche presenti nel database data una certa Categoria Nipote
     *
     * @param categoriaNipote indica la CategoriaNipote da cui si vogliono recuperare le caratteristiche
     * @return una lista di oggetti di tipo Caratteristica
     */
    List<Caratteristica> getAllCaratteristiche(CategoriaNipote categoriaNipote) throws DataException;

    /**
     * Salva nel database una nuova caratteristica o aggiorna quella esistente
     *
     * @param caratteristica l'oggetto di tipo Caratteristica da salvare
     */
    void storeCaratteristica(Caratteristica caratteristica) throws DataException;

    /**
     * Cancella dal database una caratteristica
     *
     * @param caratteristica l'oggetto di tipo Caratteristica da cancellare
     */
    void deleteCaratteristica(Caratteristica caratteristica) throws DataException;

    /**
     * Crea un oggetto di tipo CaratteristicaConValore.
     *
     * @return un oggetto di tipo CaratteristicaConValore.
     */
    CaratteristicaConValore createCaratteristicaConValore();

    /**
     * Restituisce l'oggetto CaratteristicaConValore con l'id passato come parametro
     *
     * @param key id della caratteristica con valore
     * @return l'oggetto CaratteristicaConValore con l'id passato come parametro
     */
    CaratteristicaConValore getCaratteristicaConValore(int key) throws DataException;

    /**
     * Salva nel database un oggetto di tipo CaratteristicaConValore o aggiorna quella esistente
     *
     * @param caratteristicaConValore l'oggetto di tipo CaratteristicaConValore da salvare
     */
    void storeCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore, Integer richiesta_key) throws DataException;

    /**
     * Cancella dal database un oggetto di tipo CaratteristicaConValore
     *
     * @param caratteristicaConValore l'oggetto di tipo CaratteristicaConValore da cancellare
     */
    void deleteCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore) throws DataException;

    /**
     * Restituisce tutte le CaratteristicheConValore presenti nel database data una certa Richiesta
     *
     * @param richiesta indica la Richiesta da cui si vogliono recuperare le caratteristiche con valore
     * @return una lista di oggetti di tipo CaratteristicaConValore
     */
    List<CaratteristicaConValore> getCaratteristicheConValore(Richiesta richiesta) throws DataException;

}

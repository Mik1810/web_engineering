package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;
//Generate javadoc documentation
public interface CategoriaDAO {

    /**
     * Crea un oggetto di tipo CategoriaPadre.
     *
     * @return un oggetto di tipo CatecoriaPadre.
     */
    CategoriaPadre createCategoriaPadre();

    /**
     * Crea un oggetto di tipo CategoriaFiglio.
     *
     * @return un oggetto di tipo CatecoriaFiglio.
     */
    CategoriaFiglio createCategoriaFiglio();

    /**
     * Crea un oggetto di tipo CategoriaNipote.
     *
     * @return un oggetto di tipo CatecoriaNipote.
     */
    CategoriaNipote createCategoriaNipote();

    /**
     * Restituisce l'oggetto CategoriaPadre con l'id passato come parametro
     *
     * @param categoriaPadre_key id della CategoriaPadre
     * @return l'oggetto CategoriaPadre con l'id passato come parametro
     */
    CategoriaPadre getCategoriaPadre(int categoriaPadre_key) throws DataException;

    /**
     * Restituisce l'oggetto CategoriaFiglio con l'id passato come parametro
     *
     * @param categoriaFiglio_key id della CategoriaFiglio
     * @return l'oggetto CategoriaFiglio con l'id passato come parametro
     */
    CategoriaFiglio getCategoriaFiglio(int categoriaFiglio_key) throws DataException;

    /**
     * Restituisce l'oggetto CategoriaNipote con l'id passato come parametro
     *
     * @param categoriaNipote_key id della CategoriaNipote
     * @return l'oggetto CategoriaNipote con l'id passato come parametro
     */
    CategoriaNipote getCategoriaNipote(int categoriaNipote_key) throws DataException;

    /**
     * Restituisce tutte le categorie padre presenti nel database
     *
     * @return una lista di oggetti di tipo CategoriaPadre
     */
    List<CategoriaPadre> getAllCategoriePadre() throws DataException;

    /**
     * Restituisce tutte le CategoriePadre presenti nel database paginate con un certo offset
     * @param page indica la pagina da cui si vogliono recuperare le categorie padre
     * @return una lista di oggetti di tipo CategoriaPadre
     */
    List<CategoriaPadre> getAllCategoriePadre(Integer page) throws DataException;

    /**
     * Restituisce tutte le CategorieFiglio presenti nel database
     * @return una lista di oggetti di tipo CategoriaFiglio
     */
    List<CategoriaFiglio> getAllCategorieFiglio() throws DataException;

    /**
     * Restituisce tutte le CategorieNipote presenti nel database
     * @return una lista di oggetti di tipo CategoriaNipote
     */
    List<CategoriaNipote> getAllCategorieNipote() throws DataException;

    /**
     * Restituisce tutte le CategorieFiglio presenti nel database data una certa CategoriaPadre
     *
     * @param categoriaPadre indica la CategoriaPadre da cui si vogliono recuperare le CategorieFiglio
     * @return una lista di oggetti di tipo CategoriaFiglio
     */
    List<CategoriaFiglio> getCategorieFiglioByPadre(CategoriaPadre categoriaPadre) throws DataException;

    /**
     * Restituisce tutte le CategorieNipote presenti nel database data una certa CategoriaFiglio
     *
     * @param categoriaFiglio indica la CategoriaFiglio da cui si vogliono recuperare le CategorieNipote
     * @return una lista di oggetti di tipo CategoriaNipote
     */
    List<CategoriaNipote> getCategorieNipoteByFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    /**
     * Salva nel database una nuova CategoriaPadre o aggiorna quella esistente
     *
     * @param categoriaPadre l'oggetto di tipo CategoriaPadre da salvare
     */
    void storeCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException;

    /**
     * Salva nel database una nuova CategoriaFiglio o aggiorna quella esistente
     *
     * @param categoriaFiglio l'oggetto di tipo CategoriaFiglio da salvare
     */
    void storeCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    /**
     * Salva nel database una nuova CategoriaNipote o aggiorna quella esistente
     *
     * @param categoriaNipote l'oggetto di tipo CategoriaNipote da salvare
     */
    void storeCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException;

    /**
     * Cancella dal database una CategoriaPadre
     *
     * @param categoriaPadre l'oggetto di tipo CategoriaPadre da cancellare
     */
    void deleteCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException;

    /**
     * Cancella dal database una CategoriaFiglio
     *
     * @param categoriaFiglio l'oggetto di tipo CategoriaFiglio da cancellare
     */
    void deleteCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    /**
     * Cancella dal database una CategoriaNipote
     *
     * @param categoriaNipote l'oggetto di tipo CategoriaNipote da cancellare
     */
    void deleteCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException;


}

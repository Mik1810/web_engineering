package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface CategoriaDAO {

    CategoriaPadre createCategoriaPadre();

    CategoriaFiglio createCategoriaFiglio();

    CategoriaNipote createCategoriaNipote();

    CategoriaPadre getCategoriaPadre(int categoriaPadre_key) throws DataException;

    CategoriaFiglio getCategoriaFiglio(int categoriaFiglio_key) throws DataException;

    CategoriaNipote getCategoriaNipote(int categoriaNipote_key) throws DataException;

    List<CategoriaPadre> getAllCategoriePadre() throws DataException;

    List<CategoriaPadre> getAllCategoriePadre(Integer page) throws DataException;

    List<CategoriaFiglio> getAllCategorieFiglio() throws DataException;

    List<CategoriaNipote> getAllCategorieNipote() throws DataException;

    List<CategoriaFiglio> getCategorieFiglioByPadre(CategoriaPadre categoriaPadre) throws DataException;

    List<CategoriaNipote> getCategorieNipoteByFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    void storeCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException;

    void storeCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    void storeCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException;

    void deleteCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException;

    void deleteCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException;

    void deleteCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException;


}

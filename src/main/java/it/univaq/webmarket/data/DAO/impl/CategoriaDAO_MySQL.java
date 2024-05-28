package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.CategoriaDAO;
import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.data.model.impl.proxy.CategoriaFiglioProxy;
import it.univaq.webmarket.data.model.impl.proxy.CategoriaPadreProxy;
import it.univaq.webmarket.data.model.impl.proxy.CategoriaNipoteProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO_MySQL extends DAO implements CategoriaDAO {

    private PreparedStatement sCategoriaPadreByID;
    private PreparedStatement sCategoriaFiglioByID;
    private PreparedStatement sCategoriaNipoteByID;

    private PreparedStatement sCategoriePadre;
    private PreparedStatement sCategorieFiglio;
    private PreparedStatement sCategorieNipote;

    private PreparedStatement iCategoriaPadre;
    private PreparedStatement iCategoriaFiglio;
    private PreparedStatement iCategoriaNipote;

    private PreparedStatement uCategoriaPadre;
    private PreparedStatement uCategoriaFiglio;
    private PreparedStatement uCategoriaNipote;

    private PreparedStatement sCategoriaPadreFromFiglio;
    private PreparedStatement sCategoriaFiglioFromNipote;

    private PreparedStatement dCategoriaPadre;
    private PreparedStatement dCategoriaFiglio;
    private PreparedStatement dCategoriaNipote;

    public CategoriaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try{
            super.init();
            sCategoriaPadreByID = connection.prepareStatement("SELECT * FROM categoriaPadre WHERE ID=?");
            sCategoriaFiglioByID = connection.prepareStatement("SELECT * FROM categoriaFiglio WHERE ID=?");
            sCategoriaNipoteByID = connection.prepareStatement("SELECT * FROM categoriaNipote WHERE ID=?");

            sCategoriePadre = connection.prepareStatement("SELECT ID FROM categoriaPadre");
            sCategorieFiglio = connection.prepareStatement("SELECT ID FROM categoriaFiglio");
            sCategorieNipote = connection.prepareStatement("SELECT ID FROM categoriaNipote");

            iCategoriaPadre = connection.prepareStatement("INSERT INTO categoriapadre(nome) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            iCategoriaFiglio = connection.prepareStatement("INSERT INTO categoriafiglio(nome, ID_categoria_padre) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            iCategoriaNipote = connection.prepareStatement("INSERT INTO categorianipote(nome, ID_categoria_figlio) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            uCategoriaPadre = connection.prepareStatement("UPDATE categoriapadre SET nome=? WHERE ID=? AND version=?");
            uCategoriaFiglio = connection.prepareStatement("UPDATE categoriafiglio SET nome=?, ID_categoria_padre=? WHERE ID=? AND version=?");
            uCategoriaNipote = connection.prepareStatement("UPDATE categorianipote SET nome=?, ID_categoria_figlio=? WHERE ID=? AND version=?");

            sCategoriaPadreFromFiglio = connection.prepareStatement("SELECT * FROM categoriapadre JOIN categoriafiglio ON categoriapadre.ID = categoriafiglio.ID_categoria_padre WHERE categoriafiglio.ID = ?");
            sCategoriaFiglioFromNipote = connection.prepareStatement("SELECT * FROM categoriafiglio JOIN categorianipote ON categoriafiglio.ID = categorianipote.ID_categoria_figlio WHERE categorianipote.ID = ?");

            dCategoriaPadre = connection.prepareStatement("DELETE FROM categoriapadre WHERE ID=?");
            dCategoriaFiglio = connection.prepareStatement("DELETE FROM categoriafiglio WHERE ID=?");
            dCategoriaNipote = connection.prepareStatement("DELETE FROM categorianipote WHERE ID=?");

        } catch(SQLException e) {
            throw new DataException("Error initializing webmarket data layer", e);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sCategoriaPadreByID.close();
            sCategoriaFiglioByID.close();
            sCategoriaNipoteByID.close();

            sCategoriePadre.close();
            sCategorieFiglio.close();
            sCategorieNipote.close();

            iCategoriaPadre.close();
            iCategoriaFiglio.close();
            iCategoriaNipote.close();

            uCategoriaPadre.close();
            uCategoriaFiglio.close();
            uCategoriaNipote.close();

            sCategoriaPadreFromFiglio.close();
            sCategoriaFiglioFromNipote.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public CategoriaPadre createCategoriaPadre() {
        return new CategoriaPadreProxy(getDataLayer());
    }

    @Override
    public CategoriaFiglio createCategoriaFiglio() {
        return new CategoriaFiglioProxy(getDataLayer());
    }

    @Override
    public CategoriaNipote createCategoriaNipote() {
        return new CategoriaNipoteProxy(getDataLayer());
    }

    private CategoriaPadre createCategoriaPadre(ResultSet rs) throws DataException {
        try {
            CategoriaPadreProxy cp = (CategoriaPadreProxy) createCategoriaPadre();
            cp.setKey(rs.getInt("ID"));
            cp.setNome(rs.getString("nome"));
            cp.setVersion(rs.getLong("version"));
            return cp;
        } catch (SQLException ex) {
            throw new DataException("Unable to create CategoriaPadre object form ResultSet", ex);
        }
    }

    private CategoriaFiglio createCategoriaFiglio(ResultSet rs) throws DataException {
        try {
            CategoriaFiglioProxy cf = (CategoriaFiglioProxy) createCategoriaFiglio();
            cf.setKey(rs.getInt("ID"));
            cf.setNome(rs.getString("nome"));
            cf.setCategoriaPadre_key(rs.getInt("ID_categoria_padre"));
            cf.setVersion(rs.getLong("version"));
            return cf;
        } catch (SQLException ex) {
            throw new DataException("Unable to create CategoriaFiglio object form ResultSet", ex);
        }
    }

    private CategoriaNipote createCategoriaNipote(ResultSet rs) throws DataException {
        try {
            CategoriaNipoteProxy cn = (CategoriaNipoteProxy) createCategoriaNipote();
            cn.setKey(rs.getInt("ID"));
            cn.setNome(rs.getString("nome"));
            cn.setCategoriaFiglio_key(rs.getInt("ID_categoria_figlio"));
            cn.setVersion(rs.getLong("version"));
            return cn;
        } catch (SQLException ex) {
            throw new DataException("Unable to create CategoriaNipote object form ResultSet", ex);
        }
    }

    @Override
    public CategoriaPadre getCategoriaPadre(int categoriaPadre_key) throws DataException {
        CategoriaPadre cp = null;
        if (dataLayer.getCache().has(CategoriaPadre.class, categoriaPadre_key)) {
            cp = dataLayer.getCache().get(CategoriaPadre.class, categoriaPadre_key);
        } else {
            try {
                sCategoriaPadreByID.setInt(1, categoriaPadre_key);
                try (ResultSet rs = sCategoriaPadreByID.executeQuery()) {
                    if (rs.next()) {
                        cp = createCategoriaPadre(rs);
                        dataLayer.getCache().add(CategoriaPadre.class, cp);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load CategoriaPadre by ID", ex);
            }
        }
        return cp;
    }

    @Override
    public CategoriaFiglio getCategoriaFiglio(int categoriaFiglio_key) throws DataException {
        CategoriaFiglio cf = null;
        if (dataLayer.getCache().has(CategoriaFiglio.class, categoriaFiglio_key)) {
            cf = dataLayer.getCache().get(CategoriaFiglio.class, categoriaFiglio_key);
        } else {
            try {
                sCategoriaFiglioByID.setInt(1, categoriaFiglio_key);
                try (ResultSet rs = sCategoriaFiglioByID.executeQuery()) {
                    if (rs.next()) {
                        cf = createCategoriaFiglio(rs);
                        dataLayer.getCache().add(CategoriaFiglio.class, cf);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load CategoriaFiglio by ID", ex);
            }
        }
        return cf;
    }

    @Override
    public CategoriaNipote getCategoriaNipote(int categoriaNipote_key) throws DataException {
        CategoriaNipote cn = null;
        if (dataLayer.getCache().has(CategoriaNipote.class, categoriaNipote_key)) {
            cn = dataLayer.getCache().get(CategoriaNipote.class, categoriaNipote_key);
        } else {
            try {
                sCategoriaPadreByID.setInt(1, categoriaNipote_key);
                try (ResultSet rs = sCategoriaNipoteByID.executeQuery()) {
                    if (rs.next()) {
                        cn = createCategoriaNipote(rs);
                        dataLayer.getCache().add(CategoriaNipote.class, cn);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load CategoriaNipote by ID", ex);
            }
        }
        return cn;
    }

    @Override
    public List<CategoriaPadre> getAllCategoriePadre() throws DataException {
        List<CategoriaPadre> result = new ArrayList<>();
        try {
            try (ResultSet rs = sCategoriePadre.executeQuery()){
                while (rs.next()){
                    result.add(getCategoriaPadre(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all CategoriaPadre", ex);
        }
    }

    @Override
    public List<CategoriaFiglio> getAllCategorieFiglio() throws DataException {
        return List.of();
    }

    @Override
    public List<CategoriaNipote> getAllCategorieNipote() throws DataException {
        return List.of();
    }

    @Override
    public List<CategoriaFiglio> getCategorieFiglioByPadre(CategoriaPadre categoriaPadre) throws DataException {
        return List.of();
    }

    @Override
    public List<CategoriaNipote> getCategorieNipoteByFiglio(CategoriaFiglio categoriaFiglio) throws DataException {
        return List.of();
    }

    @Override
    public void storeCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException {

    }

    @Override
    public void storeCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException {

    }

    @Override
    public void storeCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException {

    }

    @Override
    public void deleteCategoriaPadre(CategoriaPadre categoriaPadre) throws DataException {

    }

    @Override
    public void deleteCategoriaFiglio(CategoriaFiglio categoriaFiglio) throws DataException {

    }

    @Override
    public void deleteCategoriaNipote(CategoriaNipote categoriaNipote) throws DataException {

    }
}

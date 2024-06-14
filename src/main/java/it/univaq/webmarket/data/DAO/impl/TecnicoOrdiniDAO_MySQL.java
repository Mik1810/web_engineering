package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.TecnicoOrdiniDAO;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.proxy.TecnicoOrdiniProxy;
import it.univaq.webmarket.framework.data.*;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TecnicoOrdiniDAO_MySQL extends DAO implements TecnicoOrdiniDAO {

    private Integer offset = 5;

    private PreparedStatement sTecnicoOrdiniByID;
    private PreparedStatement sTecnicoOrdiniByEmail;
    private PreparedStatement iTecnicoOrdini;
    private PreparedStatement sTecniciOrdini;
    private PreparedStatement uTecnicoOrdini;
    private PreparedStatement dTecnicoOrdini;

    public TecnicoOrdiniDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sTecnicoOrdiniByID = connection.prepareStatement("SELECT * FROM tecnicoordini WHERE ID=?");
            sTecnicoOrdiniByEmail = connection.prepareStatement("SELECT * FROM tecnicoordini WHERE email=?");
            iTecnicoOrdini = connection.prepareStatement("INSERT INTO tecnicoordini(email, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            sTecniciOrdini = connection.prepareStatement("SELECT ID FROM tecnicoordini LIMIT ?, ?");
            uTecnicoOrdini = connection.prepareStatement("UPDATE tecnicoordini SET email=?,password=?,version=? WHERE ID=? and version=?");
            dTecnicoOrdini = connection.prepareStatement("DELETE FROM tecnicoordini WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sTecnicoOrdiniByID.close();
            sTecnicoOrdiniByEmail.close();
            iTecnicoOrdini.close();
            sTecniciOrdini.close();
            uTecnicoOrdini.close();
            dTecnicoOrdini.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public TecnicoOrdini createTecnicoOrdini() {
        return new TecnicoOrdiniProxy(getDataLayer());
    }

    private TecnicoOrdiniProxy createTecnicoOrdini(ResultSet rs) throws DataException {
        try {
            TecnicoOrdiniProxy to = (TecnicoOrdiniProxy) createTecnicoOrdini();
            to.setKey(rs.getInt("ID"));
            to.setEmail(rs.getString("email"));
            to.setPassword(rs.getString("password"));
            to.setVersion(rs.getLong("version"));
            return to;
        } catch (SQLException ex) {
            throw new DataException("Unable to create TecnicoOrdini object form ResultSet", ex);
        }
    }

    @Override
    public TecnicoOrdini getTecnicoOrdini(int tecnicoOrdini_key) throws DataException {
        TecnicoOrdini to = null;
        if (dataLayer.getCache().has(TecnicoOrdini.class, tecnicoOrdini_key)) {
            to = dataLayer.getCache().get(TecnicoOrdini.class, tecnicoOrdini_key);
        } else {
            try {
                sTecnicoOrdiniByID.setInt(1, tecnicoOrdini_key);
                try (ResultSet rs = sTecnicoOrdiniByID.executeQuery()) {
                    if (rs.next()) {
                        to = createTecnicoOrdini(rs);
                        dataLayer.getCache().add(TecnicoOrdini.class, to);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load TecnicoOrdini by ID", ex);
            }
        }
        return to;
    }

    @Override
    public List<TecnicoOrdini> getAllTecnicoOrdini(Integer page) throws DataException {
        List<TecnicoOrdini> result = new ArrayList<>();
        try {
            sTecniciOrdini.setInt(1, page  * offset);
            sTecniciOrdini.setInt(2, offset);
            try (ResultSet rs = sTecniciOrdini.executeQuery()) {
                while (rs.next()) {
                    result.add(getTecnicoOrdini(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load TecnicoOrdini", ex);
        }
    }

    @Override
    public TecnicoOrdini getTecnicoOrdiniByEmail(String email) throws DataException {
        try {
            sTecnicoOrdiniByEmail.setString(1, email);
            try (ResultSet rs = sTecnicoOrdiniByEmail.executeQuery()) {
                if (rs.next()) {
                    return getTecnicoOrdini(rs.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to find TecnicoOrdini", ex);
        }
        return null;
    }

    @Override
    public void storeTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException {
        try {
            if (tecnicoOrdini.getKey() != null && tecnicoOrdini.getKey() > 0) {
                if (tecnicoOrdini instanceof DataItemProxy && !((DataItemProxy) tecnicoOrdini).isModified()) {
                    //Se non è stato modificato semplicemente non faccio nulla
                    return;
                }
                uTecnicoOrdini.setString(1, tecnicoOrdini.getEmail());
                uTecnicoOrdini.setString(2, tecnicoOrdini.getPassword());

                long current_version = tecnicoOrdini.getVersion();
                long next_version = current_version + 1;

                uTecnicoOrdini.setLong(3, next_version);
                uTecnicoOrdini.setInt(4, tecnicoOrdini.getKey());
                uTecnicoOrdini.setLong(5, current_version);

                if (uTecnicoOrdini.executeUpdate() == 0) {
                    throw new OptimisticLockException(tecnicoOrdini);
                } else {
                    tecnicoOrdini.setVersion(next_version);
                }
            } else {
                iTecnicoOrdini.setString(1, tecnicoOrdini.getEmail());
                iTecnicoOrdini.setString(2, SecurityHelpers.getPasswordHashPBKDF2(tecnicoOrdini.getPassword()));

                if (iTecnicoOrdini.executeUpdate() == 1) {
                    try (ResultSet keys = iTecnicoOrdini.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            tecnicoOrdini.setKey(key);
                            dataLayer.getCache().add(TecnicoOrdini.class, tecnicoOrdini);
                        }
                    }
                }
            }
            if (tecnicoOrdini instanceof DataItemProxy) {
                ((DataItemProxy) tecnicoOrdini).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store TecnicoOrdini", ex);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException {
        try {

            //Lo cancello prima dalla cache
            dataLayer.getCache().delete(TecnicoOrdini.class, tecnicoOrdini);

            dTecnicoOrdini.setInt(1, tecnicoOrdini.getKey());
            dTecnicoOrdini.executeUpdate();

        } catch(SQLException e) {
            throw new DataException("Unable to delete TecnicoOrdini", e);
        }

    }
}

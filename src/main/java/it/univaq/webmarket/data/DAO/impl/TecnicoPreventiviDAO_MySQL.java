package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.TecnicoPreventiviDAO;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.impl.proxy.TecnicoPreventiviProxy;
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

public class TecnicoPreventiviDAO_MySQL extends DAO implements TecnicoPreventiviDAO {

    private Integer offset = 5;

    private PreparedStatement sTecnicoPreventivoByID;
    private PreparedStatement sTecnicoPreventivoByEmail;
    private PreparedStatement iTecnicoPreventivi;
    private PreparedStatement sTecnicoPreventivi;
    private PreparedStatement uTecnicoPreventivi;
    private PreparedStatement dTecnicoPreventivi;

    public TecnicoPreventiviDAO_MySQL(DataLayer d) { super(d); }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sTecnicoPreventivoByID = connection.prepareStatement("SELECT * FROM tecnicopreventivi WHERE ID=?");
            sTecnicoPreventivoByEmail = connection.prepareStatement("SELECT * FROM tecnicopreventivi WHERE email=?");
            iTecnicoPreventivi = connection.prepareStatement("INSERT INTO tecnicopreventivi(email, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            sTecnicoPreventivi = connection.prepareStatement("SELECT ID FROM tecnicopreventivi LIMIT ?, ?");
            uTecnicoPreventivi = connection.prepareStatement("UPDATE tecnicopreventivi SET email=?,password=?,version=? WHERE ID=? and version=?");
            dTecnicoPreventivi = connection.prepareStatement("DELETE FROM tecnicopreventivi WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sTecnicoPreventivoByID.close();
            sTecnicoPreventivoByEmail.close();
            iTecnicoPreventivi.close();
            sTecnicoPreventivi.close();
            uTecnicoPreventivi.close();
            dTecnicoPreventivi.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();

    }

    @Override
    public TecnicoPreventivi createTecnicoPreventivi() {
        return new TecnicoPreventiviProxy(getDataLayer());
    }

    private TecnicoPreventiviProxy createTecnicoPreventivi(ResultSet rs) throws DataException {
        try {
            TecnicoPreventiviProxy o = (TecnicoPreventiviProxy) createTecnicoPreventivi();
            o.setKey(rs.getInt("ID"));
            o.setEmail(rs.getString("email"));
            o.setPassword(rs.getString("password"));
            o.setVersion(rs.getLong("version"));
            return o;
        } catch (SQLException ex) {
            throw new DataException("Unable to create TecnicoPreventivi object form ResultSet", ex);
        }
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi(int tecnicoPreventivi_key) throws DataException {
        TecnicoPreventivi tc = null;
        if (dataLayer.getCache().has(TecnicoPreventivi.class, tecnicoPreventivi_key)) {
            tc = dataLayer.getCache().get(TecnicoPreventivi.class, tecnicoPreventivi_key);
        } else {
            try {
                sTecnicoPreventivoByID.setInt(1, tecnicoPreventivi_key);
                try (ResultSet rs = sTecnicoPreventivoByID.executeQuery()) {
                    if (rs.next()) {
                        tc = createTecnicoPreventivi(rs);
                        dataLayer.getCache().add(TecnicoPreventivi.class, tc);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Tecnico Preventivi by ID", ex);
            }
        }
        return tc;
    }

    @Override
    public List<TecnicoPreventivi> getAllTecnicoPreventivi(Integer page) throws DataException {
        List<TecnicoPreventivi> result = new ArrayList<>();
        try {
            sTecnicoPreventivi.setInt(1, page  * offset);
            sTecnicoPreventivi.setInt(2, offset);
            try (ResultSet rs = sTecnicoPreventivi.executeQuery()) {
                while (rs.next()) {
                    result.add(getTecnicoPreventivi(rs.getInt("ID")));
                }
                return result;
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Tecnico Preventivi", ex);
        }
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventiviByEmail(String email) throws DataException {
        try {
            sTecnicoPreventivoByEmail.setString(1, email);
            try (ResultSet rs = sTecnicoPreventivoByEmail.executeQuery()) {
                if (rs.next()) {
                    return getTecnicoPreventivi(rs.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to find Tecnico Preventivi", ex);
        }
        return null;
    }

    @Override
    public void storeTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException {
        try {
            if (tecnicoPreventivi.getKey() != null && tecnicoPreventivi.getKey() > 0) { //update
                if (tecnicoPreventivi instanceof DataItemProxy && !((DataItemProxy) tecnicoPreventivi).isModified()) {
                    return;
                }
                uTecnicoPreventivi.setString(1, tecnicoPreventivi.getEmail());
                uTecnicoPreventivi.setString(2, SecurityHelpers.getPasswordHashPBKDF2(tecnicoPreventivi.getPassword()));

                long current_version = tecnicoPreventivi.getVersion();
                long next_version = current_version + 1;

                uTecnicoPreventivi.setLong(3, next_version);
                uTecnicoPreventivi.setInt(4, tecnicoPreventivi.getKey());
                uTecnicoPreventivi.setLong(5, current_version);

                if (uTecnicoPreventivi.executeUpdate() == 0) {
                    throw new OptimisticLockException(tecnicoPreventivi);
                } else {
                    tecnicoPreventivi.setVersion(next_version);
                }
            } else { //insert
                iTecnicoPreventivi.setString(1, tecnicoPreventivi.getEmail());
                iTecnicoPreventivi.setString(2, SecurityHelpers.getPasswordHashPBKDF2(tecnicoPreventivi.getPassword()));

                if (iTecnicoPreventivi.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    try (ResultSet keys = iTecnicoPreventivi.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        if (keys.next()) {
                            //i campi del record sono le componenti della chiave
                            //(nel nostro caso, un solo intero)
                            //the record fields are the key componenets
                            //(a single integer in our case)
                            int key = keys.getInt(1);
                            //aggiornaimo la chiave in caso di inserimento
                            //after an insert, uopdate the object key
                            tecnicoPreventivi.setKey(key);
                            //inseriamo il nuovo oggetto nella cache
                            //add the new object to the cache
                            dataLayer.getCache().add(TecnicoPreventivi.class, tecnicoPreventivi);
                        }
                    }
                }
            }
            if (tecnicoPreventivi instanceof DataItemProxy) {
                ((DataItemProxy) tecnicoPreventivi).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store Tecnico Preventivi", ex);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException {
        try {

            //Lo cancello prima dalla cache
            dataLayer.getCache().delete(TecnicoPreventivi.class, tecnicoPreventivi);

            dTecnicoPreventivi.setInt(1, tecnicoPreventivi.getKey());
            dTecnicoPreventivi.executeUpdate();

        } catch(SQLException e) {
            throw new DataException("Unable to delete TecnicoPreventivi", e);
        }

    }
}

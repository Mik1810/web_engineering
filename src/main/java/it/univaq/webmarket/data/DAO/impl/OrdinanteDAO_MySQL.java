package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.OrdinanteDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.impl.proxy.OrdinanteProxy;
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

public class OrdinanteDAO_MySQL extends DAO implements OrdinanteDAO {

    private PreparedStatement sOrdinanteByID;
    private PreparedStatement sOrdinanteByEmail;
    private PreparedStatement iOrdinante;
    private PreparedStatement sOrdinanti;
    private PreparedStatement uOrdinante;
    private PreparedStatement dOrdinante;

    public OrdinanteDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sOrdinanteByID = connection.prepareStatement("SELECT * FROM ordinante WHERE ID=?");
            sOrdinanteByEmail = connection.prepareStatement("SELECT * FROM ordinante WHERE email=?");
            iOrdinante = connection.prepareStatement("INSERT INTO ordinante(email, password, ID_ufficio) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sOrdinanti = connection.prepareStatement("SELECT ID FROM ordinante");
            uOrdinante = connection.prepareStatement("UPDATE ordinante SET email=?, password=?, ID_ufficio=?, version=? WHERE ID=? AND version=?");
            dOrdinante = connection.prepareStatement("DELETE FROM ordinante WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sOrdinanteByID.close();
            iOrdinante.close();
            sOrdinanteByEmail.close();
            sOrdinanti.close();
            uOrdinante.close();
            dOrdinante.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public Ordinante createOrdinante() {
        return new OrdinanteProxy(getDataLayer());
    }

    private OrdinanteProxy createOrdinante(ResultSet rs) throws DataException {
        try {
            OrdinanteProxy o = (OrdinanteProxy) createOrdinante();
            o.setKey(rs.getInt("ID"));
            o.setEmail(rs.getString("email"));
            o.setPassword(rs.getString("password"));
            o.setVersion(rs.getLong("version"));
            o.setUfficio_key(rs.getInt("ID_ufficio"));
            return o;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Ordinante object form ResultSet", ex);
        }
    }

    @Override
    public Ordinante getOrdinante(int ordinante_key) throws DataException {
        Ordinante o = null;
        if (dataLayer.getCache().has(Ordinante.class, ordinante_key)) {
            o = dataLayer.getCache().get(Ordinante.class, ordinante_key);
        } else {
            try {
                sOrdinanteByID.setInt(1, ordinante_key);
                try (ResultSet rs = sOrdinanteByID.executeQuery()) {
                    if (rs.next()) {
                        o = createOrdinante(rs);
                        dataLayer.getCache().add(Ordinante.class, o);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Ordinante by ID", ex);
            }
        }
        return o;
    }

    @Override
    public List<Ordinante> getAllOrdinanti() throws DataException {
        List<Ordinante> result = new ArrayList<>();

        try (ResultSet rs = sOrdinanti.executeQuery()) {
            while (rs.next()) {
                result.add(getOrdinante(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ordinante", ex);
        }
        return result;
    }

    @Override
    public Ordinante getOrdinanteByEmail(String email) throws DataException {
        try {
            sOrdinanteByEmail.setString(1, email);
            try (ResultSet rs = sOrdinanteByEmail.executeQuery()) {
                if (rs.next()) {
                    return getOrdinante(rs.getInt("ID"));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to find Ordinante", ex);
        }
        return null;
    }

    @Override
    public void storeOrdinante(Ordinante ordinante) throws DataException {
        try {
            if (ordinante.getKey() != null && ordinante.getKey() > 0) { //update
                if (ordinante instanceof DataItemProxy && !((DataItemProxy) ordinante).isModified()) {
                    return;
                }
                uOrdinante.setString(1, ordinante.getEmail());
                uOrdinante.setString(2, ordinante.getPassword());
                uOrdinante.setInt(3, ordinante.getUfficio().getKey());

                long current_version = ordinante.getVersion();
                long next_version = current_version + 1;

                uOrdinante.setLong(4, next_version);
                uOrdinante.setInt(5, ordinante.getKey());
                uOrdinante.setLong(6, current_version);

                if (uOrdinante.executeUpdate() == 0) {
                    throw new OptimisticLockException(ordinante);
                } else {
                    ordinante.setVersion(next_version);
                }
            } else { //insert
                iOrdinante.setString(1, ordinante.getEmail());
                iOrdinante.setString(2, SecurityHelpers.getPasswordHashPBKDF2(ordinante.getPassword()));
                iOrdinante.setInt(3, ordinante.getUfficio().getKey());

                if (iOrdinante.executeUpdate() == 1) {
                    try (ResultSet keys = iOrdinante.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            ordinante.setKey(key);
                            dataLayer.getCache().add(Ordinante.class, ordinante);
                        }
                    }
                }
            }
            if (ordinante instanceof DataItemProxy) {
                ((DataItemProxy) ordinante).setModified(false);
            }
        } catch (SQLException | OptimisticLockException ex) {
            throw new DataException("Unable to store Ordinante", ex);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrdinante(Ordinante ordinante) throws DataException {
        try {

            //Lo cancello prima dalla cache
            dataLayer.getCache().delete(Ordinante.class, ordinante);

            dOrdinante.setInt(1, ordinante.getKey());
            dOrdinante.executeUpdate();

        } catch(SQLException e) {
            throw new DataException("Unable to delete Ordinante", e);
        }

    }
}

package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.OrdinanteDAO;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.impl.proxy.OrdinanteProxy;
import it.univaq.webmarket.framework.data.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdinanteDAO_MySQL extends DAO implements OrdinanteDAO {

    private PreparedStatement sOrdinanteByID;
    private PreparedStatement sOrdinanteByEmail;
    private PreparedStatement iOrdinante;
    private PreparedStatement sOrdinanti;
    private PreparedStatement uOrdinante;

    public OrdinanteDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sOrdinanteByID = connection.prepareStatement("SELECT * FROM ordinante WHERE ID=?");
            sOrdinanteByEmail = connection.prepareStatement("SELECT * FROM ordinante WHERE email=?");
            iOrdinante = connection.prepareStatement("INSERT INTO ordinante(email, password) VALUES (?, ?)");
            sOrdinanti = connection.prepareStatement("SELECT ID FROM ordinante");
            uOrdinante = connection.prepareStatement("UPDATE ordinante SET email=?,password=?,version=? WHERE ID=? and version=?");
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
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                if (ordinante instanceof DataItemProxy && !((DataItemProxy) ordinante).isModified()) {
                    return;
                }
                uOrdinante.setString(1, ordinante.getEmail());
                uOrdinante.setString(2, ordinante.getPassword());

                long current_version = ordinante.getVersion();
                long next_version = current_version + 1;

                uOrdinante.setLong(3, next_version);
                uOrdinante.setInt(4, ordinante.getKey());
                uOrdinante.setLong(5, current_version);

                if (uOrdinante.executeUpdate() == 0) {
                    throw new OptimisticLockException(ordinante);
                } else {
                    ordinante.setVersion(next_version);
                }
            } else { //insert
                iOrdinante.setString(1, ordinante.getEmail());
                iOrdinante.setString(2, ordinante.getPassword());

                if (iOrdinante.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    try (ResultSet keys = iOrdinante.getGeneratedKeys()) {
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
                            ordinante.setKey(key);
                            //inseriamo il nuovo oggetto nella cache
                            //add the new object to the cache
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
        }
    }
}
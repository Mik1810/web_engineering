package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.UfficioDAO;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.proxy.UfficioProxy;
import it.univaq.webmarket.framework.data.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UfficioDAO_MySQL extends DAO implements UfficioDAO {

    private PreparedStatement sUfficioByID;
    private PreparedStatement sUffici;
    private PreparedStatement iUfficio;
    private PreparedStatement uUfficio;
    private PreparedStatement dUfficio;

    public UfficioDAO_MySQL(DataLayer d) { super(d); }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            sUfficioByID = connection.prepareStatement("SELECT * FROM ufficio WHERE ID=?");
            sUffici = connection.prepareStatement("SELECT ID FROM ufficio");
            iUfficio = connection.prepareStatement("INSERT INTO ufficio(sede, numero, piano, telefono, citta) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            uUfficio = connection.prepareStatement("UPDATE ufficio SET sede=?, numero=?, piano=?, telefono=?, citta=?, version=? WHERE ID=? AND version=?");
            dUfficio = connection.prepareStatement("DELETE FROM ufficio WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing webmarket data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sUfficioByID.close();
            sUffici.close();
            iUfficio.close();
            uUfficio.close();
            dUfficio.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public Ufficio createUfficio() {
        return new UfficioProxy(getDataLayer());
    }

    private UfficioProxy createUfficio(ResultSet rs) throws DataException {
        UfficioProxy ufficio = new UfficioProxy(getDataLayer());
        try {
            ufficio.setKey(rs.getInt("ID"));
            ufficio.setSede(rs.getString("sede"));
            ufficio.setNumeroUfficio(rs.getInt("numero"));
            ufficio.setPiano(rs.getInt("piano"));
            ufficio.setNumeroTelefono(rs.getString("telefono"));
            ufficio.setCitta(rs.getString("citta"));
            ufficio.setVersion(rs.getLong("version"));
            return ufficio;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Ufficio object form ResultSet", ex);
        }
    }
    @Override
    public Ufficio getUfficio(int ufficio_key) throws DataException {
        Ufficio ufficio = null;
        if (dataLayer.getCache().has(Ufficio.class, ufficio_key)) {
            ufficio = dataLayer.getCache().get(Ufficio.class, ufficio_key);
        } else {
            try {
                sUfficioByID.setInt(1, ufficio_key);
                try (ResultSet rs = sUfficioByID.executeQuery()) {
                    if (rs.next()) {
                        ufficio = createUfficio(rs);
                        dataLayer.getCache().add(Ufficio.class, ufficio);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load Ufficio by ID", ex);
            }
        }
        return ufficio;
    }

    @Override
    public void storeUfficio(Ufficio ufficio) throws DataException {
        try {
            if (ufficio.getKey() != null && ufficio.getKey() > 0) {
                // Update
                if (ufficio instanceof DataItemProxy && !((DataItemProxy) ufficio).isModified()) {
                    return;
                }

                /*
                sede=1,
                numero=2,
                piano=3,
                telefono=4,
                citta=5,
                version=6
                WHERE ID=7
                AND version=8"
                */
                uUfficio.setString(1, ufficio.getSede());
                uUfficio.setInt(2, ufficio.getNumeroUfficio());
                uUfficio.setInt(3, ufficio.getPiano());
                uUfficio.setString(4, ufficio.getNumeroTelefono());
                uUfficio.setString(5, ufficio.getCitta());

                long current_version = ufficio.getVersion();
                long next_version = current_version + 1;

                uUfficio.setLong(6, next_version);
                uUfficio.setInt(7, ufficio.getKey());
                uUfficio.setLong(8, current_version);

                if (uUfficio.executeUpdate() == 0) {
                    throw new OptimisticLockException(ufficio);
                } else {
                    ufficio.setVersion(next_version);
                }
            } else {
                // Insert
                iUfficio.setString(1, ufficio.getSede());
                iUfficio.setInt(2, ufficio.getNumeroUfficio());
                iUfficio.setInt(3, ufficio.getPiano());
                iUfficio.setString(4, ufficio.getNumeroTelefono());
                iUfficio.setString(5, ufficio.getCitta());
                if (iUfficio.executeUpdate() == 1) {
                    try (ResultSet keys = iUfficio.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            ufficio.setKey(key);
                            dataLayer.getCache().add(Ufficio.class, ufficio);
                        }
                    }
                }
            }
            if (ufficio instanceof DataItemProxy) {
                ((DataItemProxy) ufficio).setModified(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store Ufficio", ex);
        }
    }

    @Override
    public void deleteUfficio(Ufficio ufficio) throws DataException {
        try {
            dataLayer.getCache().delete(Ufficio.class, ufficio.getKey());

            dUfficio.setInt(1, ufficio.getKey());
            dUfficio.executeUpdate();

        } catch (SQLException ex) {
            throw new DataException("Unable to delete Ufficio", ex);
        }
    }

    @Override
    public List<Ufficio> getAllUffici() throws DataException {
        List<Ufficio> result = new ArrayList<>();

        try (ResultSet rs = sUffici.executeQuery()) {
            while (rs.next()) {
                result.add(getUfficio(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Ufficio", ex);
        }
        return result;
    }
}

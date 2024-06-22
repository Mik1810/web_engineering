package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.data.model.impl.CaratteristicaConValoreImpl;
import it.univaq.webmarket.data.model.impl.proxy.CaratteristicaConValoreProxy;
import it.univaq.webmarket.data.model.impl.proxy.CaratteristicaProxy;
import it.univaq.webmarket.data.model.impl.proxy.PropostaProxy;
import it.univaq.webmarket.framework.data.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaratteristicaDAO_MySQL extends DAO implements CaratteristicaDAO {

    private Integer offset = 5;

    private PreparedStatement iCaratteristica;
    private PreparedStatement sCaratteristicaByID;
    private PreparedStatement sAllCaratteristiche;
    private PreparedStatement uCaratteristica;
    private PreparedStatement dCaratteristica;
    private PreparedStatement sCaratteristicheByCategoriaNipote;

    private PreparedStatement sCaratteristicaConValoreByID;
    private PreparedStatement sCaratteristicheConValore;
    private PreparedStatement iCaratteristicaConValore;
    private PreparedStatement uCaratteristicaConValore;
    private PreparedStatement dCaratteristicaConValore;

    public CaratteristicaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            iCaratteristica = connection.prepareStatement("INSERT INTO caratteristica(nome, unita_di_misura, ID_categoria_nipote) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            sCaratteristicaByID = connection.prepareStatement("SELECT * FROM caratteristica WHERE ID=?");
            sAllCaratteristiche = connection.prepareStatement("SELECT ID FROM caratteristica LIMIT ?, ?");
            uCaratteristica = connection.prepareStatement("UPDATE caratteristica SET nome=?, unita_di_misura=?, ID_categoria_nipote=?, version=? WHERE ID=? AND version=?");
            dCaratteristica = connection.prepareStatement("DELETE FROM caratteristica WHERE ID=?");
            sCaratteristicheByCategoriaNipote = connection.prepareStatement("SELECT * FROM caratteristica WHERE ID_categoria_nipote = ?");

            sCaratteristicaConValoreByID = connection.prepareStatement("SELECT * FROM composta WHERE ID=?");
            sCaratteristicheConValore = connection.prepareStatement("SELECT * FROM composta WHERE ID_richiesta = ?");
            iCaratteristicaConValore = connection.prepareStatement("INSERT INTO composta(ID_caratteristica, ID_richiesta, valore) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            uCaratteristicaConValore = connection.prepareStatement("UPDATE composta SET ID_caratteristica=?, ID_richiesta=?, valore=?, version=? WHERE ID=? AND version=?");
            dCaratteristicaConValore = connection.prepareStatement("DELETE FROM composta WHERE ID=?");
        } catch (Exception e) {
            throw new DataException("Errore inizializzazione data layer", e);
        }
    }


    public void destroy() throws DataException {
        try {
            iCaratteristica.close();
            sCaratteristicaByID.close();
            sAllCaratteristiche.close();
            uCaratteristica.close();
            dCaratteristica.close();
            sCaratteristicheByCategoriaNipote.close();

            sCaratteristicaConValoreByID.close();
            sCaratteristicheConValore.close();
            iCaratteristicaConValore.close();
            uCaratteristicaConValore.close();
            dCaratteristicaConValore.close();
        } catch (SQLException ex) {
            throw new DataException("Can't destroy connections", ex);
        }
        super.destroy();
    }

    @Override
    public Caratteristica createCaratteristica() {
        return new CaratteristicaProxy(getDataLayer());
    }

    @Override
    public CaratteristicaConValore createCaratteristicaConValore() {
        return new CaratteristicaConValoreProxy(getDataLayer());
    }


    public CaratteristicaProxy createCaratteristica(ResultSet rs) throws DataException {
        try {
            CaratteristicaProxy caratteristica = new CaratteristicaProxy(getDataLayer());
            caratteristica.setKey(rs.getInt("ID"));
            caratteristica.setNome(rs.getString("nome"));
            caratteristica.setUnitaMisura(rs.getString("unita_di_misura"));
            caratteristica.setCategoriaNipote_key(rs.getInt("ID_categoria_nipote"));
            caratteristica.setVersion(rs.getLong("version"));

            return caratteristica;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Caratteristica object form ResultSet", ex);
        }
    }

    public CaratteristicaConValoreProxy createCaratteristicaConValore(ResultSet rs) throws DataException {
        try {
            CaratteristicaConValoreProxy caratteristicaConValore = new CaratteristicaConValoreProxy(getDataLayer());
            caratteristicaConValore.setKey(rs.getInt("ID"));
            caratteristicaConValore.setValore(rs.getString("valore"));
            caratteristicaConValore.setCaratteristica_key(rs.getInt("ID_caratteristica"));
            caratteristicaConValore.setVersion(rs.getLong("version"));
            return caratteristicaConValore;
        } catch (SQLException ex) {
            throw new DataException("Unable to create CaratteristicaConValore object form ResultSet", ex);
        }
    }


    @Override
    public Caratteristica getCaratteristica(int key) throws DataException {
        Caratteristica caratteristica = null;
        if (dataLayer.getCache().has(Caratteristica.class, key)) {
            caratteristica = dataLayer.getCache().get(Caratteristica.class, key);
        } else {
            try {
                sCaratteristicaByID.setInt(1, key);
                try (ResultSet rs = sCaratteristicaByID.executeQuery()) {
                    if (rs.next()) {
                        caratteristica = createCaratteristica(rs);
                        dataLayer.getCache().add(Caratteristica.class, caratteristica);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to get Caratteristica by ID", ex);
            }
        }
        return caratteristica;
    }


    @Override
    public List<Caratteristica> getAllCaratteristiche(Integer page) throws DataException {
        List<Caratteristica> result = new ArrayList<>();
        try {
            sAllCaratteristiche.setInt(1, page * offset);
            sAllCaratteristiche.setInt(2, offset);
            try (ResultSet rs = sAllCaratteristiche.executeQuery()) {
                while (rs.next()) {
                    result.add(getCaratteristica(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all Caratteristica", ex);
        }
    }

    @Override
    public List<Caratteristica> getAllCaratteristiche(CategoriaNipote categoriaNipote) throws DataException {
        List<Caratteristica> result = new ArrayList<>();
        try {
            sCaratteristicheByCategoriaNipote.setInt(1, categoriaNipote.getKey());
            try (ResultSet rs = sCaratteristicheByCategoriaNipote.executeQuery()) {
                while (rs.next()) {
                    result.add(getCaratteristica(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all Caratteristica from CategoriaNipote", ex);
        }
    }

    @Override
    public void storeCaratteristica(Caratteristica caratteristica) throws DataException {
        try {
            if (caratteristica.getKey() != null && caratteristica.getKey() > 0) {
                if (caratteristica instanceof CaratteristicaProxy && !((CaratteristicaProxy) caratteristica).isModified()) {
                    return;
                }
                uCaratteristica.setString(1, caratteristica.getNome());
                uCaratteristica.setString(2, caratteristica.getUnitaMisura());
                uCaratteristica.setInt(3, caratteristica.getCategoriaNipote().getKey());

                long current_version = caratteristica.getVersion();
                long next_version = current_version + 1;

                uCaratteristica.setLong(4, next_version);
                uCaratteristica.setInt(5, caratteristica.getKey());
                uCaratteristica.setLong(6, current_version);

                if (uCaratteristica.executeUpdate() == 0) {
                    throw new OptimisticLockException(caratteristica);
                } else {
                    caratteristica.setVersion(next_version);
                }


            } else {
                iCaratteristica.setString(1, caratteristica.getNome());
                iCaratteristica.setString(2, caratteristica.getUnitaMisura());
                iCaratteristica.setInt(3, caratteristica.getCategoriaNipote().getKey());

                if (iCaratteristica.executeUpdate() == 1) {
                    try (ResultSet keys = iCaratteristica.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            caratteristica.setKey(key);
                            dataLayer.getCache().add(Caratteristica.class, caratteristica);
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store Caratteristica", ex);
        }
    }

    @Override
    public void deleteCaratteristica(Caratteristica caratteristica) throws DataException {
        try {

            dataLayer.getCache().delete(Caratteristica.class, caratteristica);
            dCaratteristica.setInt(1, caratteristica.getKey());
            dCaratteristica.executeUpdate();

        } catch (SQLException e) {
            throw new DataException("Unable to delete Caratteristica", e);
        }
    }

    @Override
    public CaratteristicaConValore getCaratteristicaConValore(int key) throws DataException {
        CaratteristicaConValore caratteristicaConValore = null;
        if (dataLayer.getCache().has(CaratteristicaConValore.class, key)) {
            caratteristicaConValore = dataLayer.getCache().get(CaratteristicaConValore.class, key);
        } else {
            try {
                sCaratteristicaConValoreByID.setInt(1, key);
                try (ResultSet rs = sCaratteristicaConValoreByID.executeQuery()) {
                    if (rs.next()) {
                        caratteristicaConValore = createCaratteristicaConValore(rs);
                        dataLayer.getCache().add(CaratteristicaConValore.class, caratteristicaConValore);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to get CaratteristicaConValore by ID", ex);
            }
        }
        return caratteristicaConValore;
    }

    @Override
    public void storeCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore, Integer richiesta_key) throws DataException {
        try {
            if (caratteristicaConValore.getKey() != null && caratteristicaConValore.getKey() > 0) {
                if (caratteristicaConValore instanceof CaratteristicaConValoreProxy && !((CaratteristicaConValoreProxy) caratteristicaConValore).isModified()) {
                    return;
                }
                /*
                ID_caratteristica=1,
                 ID_richiesta=2,
                 valore=3,
                 version=4
                 ID=5
                 version=6
                 */
                uCaratteristicaConValore.setInt(1, caratteristicaConValore.getCaratteristica().getKey());
                uCaratteristicaConValore.setInt(2, richiesta_key);
                uCaratteristicaConValore.setString(3, caratteristicaConValore.getValore());

                long current_version = caratteristicaConValore.getVersion();
                long next_version = current_version + 1;

                uCaratteristicaConValore.setLong(4, next_version);
                uCaratteristicaConValore.setInt(5, caratteristicaConValore.getKey());
                uCaratteristicaConValore.setLong(6, current_version);

                if (uCaratteristicaConValore.executeUpdate() == 0) {
                    throw new OptimisticLockException(caratteristicaConValore);
                } else {
                    caratteristicaConValore.setVersion(next_version);
                }


            } else {
                /*
                 * ID_caratteristica=1
                 * ID_richiesta=2
                 * valore=3
                 */
                iCaratteristicaConValore.setInt(1, caratteristicaConValore.getCaratteristica().getKey());
                iCaratteristicaConValore.setInt(2, richiesta_key);
                iCaratteristicaConValore.setString(3, caratteristicaConValore.getValore());

                if (iCaratteristicaConValore.executeUpdate() == 1) {
                    try (ResultSet keys = iCaratteristicaConValore.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            caratteristicaConValore.setKey(key);
                            dataLayer.getCache().add(CaratteristicaConValore.class, caratteristicaConValore);
                        }
                    }
                }

            }
            if (caratteristicaConValore instanceof DataItemProxy) {
                ((DataItemProxy) caratteristicaConValore).setModified(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store CaratteristicaConValore", ex);
        }
    }

    @Override
    public void deleteCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore) throws DataException {
        try {
            dataLayer.getCache().delete(CaratteristicaConValore.class, caratteristicaConValore);
            dCaratteristicaConValore.setInt(1, caratteristicaConValore.getKey());
            dCaratteristicaConValore.executeUpdate();
        } catch (SQLException e) {
            throw new DataException("Unable to delete CaratteristicaConValore", e);
        }
    }


    @Override
    public List<CaratteristicaConValore> getCaratteristicheConValore(Richiesta richiesta) throws DataException {
        List<CaratteristicaConValore> caratteristicheConValore = new ArrayList<>();

        try {
            sCaratteristicheConValore.setInt(1, richiesta.getKey());
            try (ResultSet rs = sCaratteristicheConValore.executeQuery()) {
                while (rs.next()) {
                    CaratteristicaConValore caratteristicaConValore = createCaratteristicaConValore();
                    caratteristicaConValore.setKey(rs.getInt("ID"));
                    caratteristicaConValore.setVersion(rs.getLong("version"));
                    caratteristicaConValore.setCaratteristica(getCaratteristica(rs.getInt("ID_caratteristica")));
                    caratteristicaConValore.setValore(rs.getString("valore"));
                    caratteristicheConValore.add(caratteristicaConValore);
                }
            }
            return caratteristicheConValore;
        } catch (SQLException ex) {
            throw new DataException("Error loading all CaratteristicaConValore from Richiesta", ex);
        }
    }
}

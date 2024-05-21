package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.impl.proxy.CaratteristicaProxy;
import it.univaq.webmarket.data.model.impl.proxy.PropostaProxy;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;
import it.univaq.webmarket.framework.data.OptimisticLockException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CaratteristicaDAO_MySQL extends DAO implements CaratteristicaDAO {

    private PreparedStatement iCaratteristica;
    private PreparedStatement sCaratteristicaByID;
    private PreparedStatement sAllCaratteristiche;
    private PreparedStatement uCaratteristica;
    private PreparedStatement dCaratteristica;

    public CaratteristicaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            iCaratteristica = connection.prepareStatement("INSERT INTO Caratteristica(nome, unita_di_misura, ID_categoria_nipote) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            sCaratteristicaByID = connection.prepareStatement("SELECT * FROM Caratteristica WHERE ID=?");
            sAllCaratteristiche = connection.prepareStatement("SELECT ID FROM Caratteristica");
            uCaratteristica = connection.prepareStatement("UPDATE Caratteristica SET nome=?, unita_di_misura=?, ID_categoria_nipote=?, version=? WHERE ID=? AND version=?");
            dCaratteristica = connection.prepareStatement("DELETE FROM Caratteristica WHERE ID=?");
        } catch (Exception e) {
            throw new DataException("Errore inizializzazione data layer", e);
        }
    }


    public void destroy() throws DataException {
        try {
            iCaratteristica.close();
            sCaratteristicaByID.close();
            sAllCaratteristiche.close();

        } catch (SQLException ex) {
            throw new DataException("Can't destroy connections", ex);
        }
        super.destroy();
    }

    @Override
    public Caratteristica createCaratteristica() {
        return new CaratteristicaProxy(getDataLayer());
    }

    public CaratteristicaProxy createCaratteristica(ResultSet rs)throws DataException {
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


    @Override
    public Caratteristica getCaratteristica(int key)throws DataException {
        Caratteristica caratteristica = null;
        if(dataLayer.getCache().has(Caratteristica.class, key)){
            caratteristica = dataLayer.getCache().get(Caratteristica.class, key);
        }else{
            try {
                sCaratteristicaByID.setInt(1,key);
                try (ResultSet rs = sCaratteristicaByID.executeQuery()){
                    if(rs.next()){
                        caratteristica = createCaratteristica(rs);
                        dataLayer.getCache().add(Caratteristica.class, caratteristica);
                    }
                }
            }catch (SQLException ex){
                throw new DataException("Unable to get Caratteristica by ID", ex);
            }
        }
        return caratteristica;
    }


    @Override
    public List<Caratteristica> getAllCaratteristiche() throws DataException {
        List<Caratteristica> result = null;
        try {
            try (ResultSet rs = sAllCaratteristiche.executeQuery()){
                while (rs.next()){
                    result.add(getCaratteristica(rs.getInt("ID")));
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Error loading all Caratteristica", ex);
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
                iCaratteristica.executeUpdate();

                if(iCaratteristica.executeUpdate() == 1){
                    try(ResultSet keys = iCaratteristica.getGeneratedKeys()){
                        if(keys.next()){
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

        } catch(SQLException e) {
            throw new DataException("Unable to delete Caratteristica", e);
        }
    }
}

package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CaratteristicaDAO_MySQL extends DAO implements CaratteristicaDAO {

    private PreparedStatement iCaratteristica;

    private PreparedStatement sCaratteristicaByID;

    private PreparedStatement sAllCaratteristiche;




    public CaratteristicaDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();
            iCaratteristica = connection.prepareStatement("INSERT INTO Caratteristica(nome, unita_di_misura) VALUES (?, ?)");
            sCaratteristicaByID = connection.prepareStatement("SELECT * FROM Caratteristica WHERE ID=?");
            sAllCaratteristiche = connection.prepareStatement("SELECT ID FROM Caratteristica");
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
        return null;
    }

    @Override
    public Caratteristica getCaratteristica(int key) {
        return null;
    }

    @Override
    public List<Caratteristica> getAllCaratteristiche() {
        return null;
    }

    @Override
    public void storeCaratteristica(Caratteristica caratteristica) {

    }
}

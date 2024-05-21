package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface CaratteristicaDAO {
    Caratteristica createCaratteristica();

    Caratteristica getCaratteristica(int key) throws DataException;

    List<Caratteristica> getAllCaratteristiche() throws DataException;

    void storeCaratteristica(Caratteristica caratteristica) throws DataException;

    void deleteCaratteristica(Caratteristica caratteristica) throws DataException;

}

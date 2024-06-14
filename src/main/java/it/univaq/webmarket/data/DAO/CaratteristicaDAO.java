package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface CaratteristicaDAO {

    Caratteristica createCaratteristica();

    Caratteristica getCaratteristica(int key) throws DataException;

    List<Caratteristica> getAllCaratteristiche(Integer page) throws DataException;

    void storeCaratteristica(Caratteristica caratteristica) throws DataException;

    void deleteCaratteristica(Caratteristica caratteristica) throws DataException;


    CaratteristicaConValore createCaratteristicaConValore();

    CaratteristicaConValore getCaratteristicaConValore(int key) throws DataException;

    void storeCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore, Integer richiesta_key) throws DataException;

    void deleteCaratteristicaConValore(CaratteristicaConValore caratteristicaConValore) throws DataException;

    List<CaratteristicaConValore> getCaratteristicheConValore(Richiesta richiesta) throws DataException;

}

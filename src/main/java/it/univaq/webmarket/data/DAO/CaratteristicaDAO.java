package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Caratteristica;

import java.util.List;

public interface CaratteristicaDAO {
    Caratteristica createCaratteristica();

    Caratteristica getCaratteristica(int key);

    List<Caratteristica> getAllCaratteristiche();
}

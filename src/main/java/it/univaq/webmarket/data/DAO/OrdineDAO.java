package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Ordine;

import java.util.List;

public interface    OrdineDAO {

    Ordine createOrdine();

    Ordine getOrdine(int key);

    List<Ordine> getAllOrdini();

    void updateOrdine(Ordine ordine);


}

package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

import java.util.List;

// Forse non serve, credo basti il DAO
public interface StoricoOrdini extends DataItem<Integer> {

    Ordinante getOrdinante();

    void setOrdinante(Ordinante ordinante);

    List<OrdineAcquisto> getOrdini();

    void setOrdini(List<OrdineAcquisto> ordini);
}

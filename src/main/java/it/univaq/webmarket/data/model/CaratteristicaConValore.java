package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface CaratteristicaConValore extends DataItem<Integer> {

    Caratteristica getCaratteristica();

    void setCaratteristica(Caratteristica caratteristica);

    String getValore();

    void setValore(String valore);
}

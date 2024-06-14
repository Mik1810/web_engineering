package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CaratteristicaConValoreImpl extends DataItemImpl<Integer> implements CaratteristicaConValore {

    private Caratteristica caratteristica;
    private String valore;

    public CaratteristicaConValoreImpl() {
        super();
        this.caratteristica = null;
        this.valore = "";
    }

    @Override
    public Caratteristica getCaratteristica() {
        return caratteristica;
    }

    @Override
    public void setCaratteristica(Caratteristica caratteristica) {
        this.caratteristica = caratteristica;
    }

    @Override
    public String getValore() {
        return valore;
    }

    @Override
    public void setValore(String valore) {
        this.valore = valore;
    }
}

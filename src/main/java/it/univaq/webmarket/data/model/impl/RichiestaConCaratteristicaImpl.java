package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaConCaratteristica;
import it.univaq.webmarket.data.model.impl.CaratteristicaImpl;
import it.univaq.webmarket.framework.data.DataItemImpl;
import it.univaq.webmarket.model.RichiestaAcquisto;

public class RichiestaConCaratteristicaImpl extends DataItemImpl<Integer> implements RichiestaConCaratteristica {

    private RichiestaAcquisto richiestaAcquisto;
    private CaratteristicaImpl caratteristica;
    private String valore;

    public RichiestaConCaratteristicaImpl() {
        this.richiestaAcquisto = null;
        this.caratteristica = null;
        this.valore = "";
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto() {
        return richiestaAcquisto;
    }

    @Override
    public void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) {
        this.richiestaAcquisto = richiestaAcquisto;
    }

    @Override
    public CaratteristicaImpl getCaratteristica() {
        return caratteristica;
    }

    @Override
    public void setCaratteristica(CaratteristicaImpl caratteristica) {
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


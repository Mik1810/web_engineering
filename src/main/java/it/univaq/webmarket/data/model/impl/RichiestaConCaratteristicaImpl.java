package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.RichiestaConCaratteristica;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class RichiestaConCaratteristicaImpl extends DataItemImpl<Integer> implements RichiestaConCaratteristica {

    private RichiestaAcquisto richiestaAcquisto;
    private Caratteristica caratteristica;
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


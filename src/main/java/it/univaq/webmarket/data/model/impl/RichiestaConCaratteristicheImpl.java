package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.util.Map;

public class RichiestaConCaratteristicheImpl extends DataItemImpl<Integer> implements RichiestaConCaratteristiche {

    private Richiesta richiesta;
    private Map<Caratteristica, String> caratteristiche;

    public RichiestaConCaratteristicheImpl() {
        super();
        this.richiesta = null;
        this.caratteristiche = null;
    }

    @Override
    public Richiesta getRichiesta() {
        return richiesta;
    }

    @Override
    public void setRichiesta(Richiesta richiesta) { this.richiesta = richiesta; }

    @Override
    public void setKey(Integer key) {
        super.setKey(key);
    }

    @Override
    public Map<Caratteristica, String> getCaratteristiche() {
        return caratteristiche;
    }

    @Override
    public void setCaratteristiche(Map<Caratteristica, String> caratteristiche) {
        this.caratteristiche = caratteristiche;
    }

    @Override
    public String toString() {
        return "RichiestaConCaratteristicheImpl{" +
                "richiesta=" + richiesta +
                ", caratteristiche=" + caratteristiche +
                '}';
    }
}


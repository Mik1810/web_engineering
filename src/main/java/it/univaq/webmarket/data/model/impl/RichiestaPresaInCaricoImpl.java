package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.RichiestaConCaratteristiche;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class RichiestaPresaInCaricoImpl extends DataItemImpl<Integer> implements RichiestaPresaInCarico {

    private RichiestaConCaratteristiche richiestaConCaratteristiche;
    private TecnicoPreventivi tecnicoPreventivi;

    public RichiestaPresaInCaricoImpl() {
        super();
        this.richiestaConCaratteristiche = null;
        this.tecnicoPreventivi = null;
    }

    @Override
    public RichiestaConCaratteristiche getRichiestaAcquisto() {
        return richiestaConCaratteristiche;
    }

    @Override
    public void setRichiestaAcquisto(RichiestaConCaratteristiche richiestaConCaratteristiche) {
        this.richiestaConCaratteristiche = richiestaConCaratteristiche;
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi() {
        return tecnicoPreventivi;
    }

    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        this.tecnicoPreventivi = tecnicoPreventivi;
    }
}

package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.framework.data.DataItemImpl;
import it.univaq.webmarket.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoPreventiviImpl;

public class RichiestaPresaInCaricoImpl extends DataItemImpl<Integer> implements RichiestaPresaInCarico {

    private Integer id;
    private RichiestaAcquisto richiestaAcquisto;
    private TecnicoPreventiviImpl tecnicoPreventivi;

    public RichiestaPresaInCaricoImpl() {
        this.id = null;
        this.richiestaAcquisto = null;
        this.tecnicoPreventivi = null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public RichiestaAcquisto getRichiestaAcquisto() {
        return richiestaAcquisto;
    }

    @Override
    public void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto) {
        this.richiestaAcquisto = richiestaAcquisto;
    }

    public TecnicoPreventiviImpl getTecnicoPreventivi() {
        return tecnicoPreventivi;
    }

    public void setTecnicoPreventiviImpl(TecnicoPreventiviImpl tecnicoPreventivi) {
        this.tecnicoPreventivi = tecnicoPreventivi;
    }
}

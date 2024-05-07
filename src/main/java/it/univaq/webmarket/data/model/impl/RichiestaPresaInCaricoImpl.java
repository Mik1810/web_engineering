package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class RichiestaPresaInCaricoImpl extends DataItemImpl<Integer> implements RichiestaPresaInCarico {

    private RichiestaAcquisto richiestaAcquisto;
    private TecnicoPreventivi tecnicoPreventivi;

    public RichiestaPresaInCaricoImpl() {
        super();
        this.richiestaAcquisto = null;
        this.tecnicoPreventivi = null;
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
    public TecnicoPreventivi getTecnicoPreventivi() {
        return tecnicoPreventivi;
    }

    @Override
    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        this.tecnicoPreventivi = tecnicoPreventivi;
    }
}

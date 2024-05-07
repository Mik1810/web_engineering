package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataItemImpl;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoPreventiviImpl;

public class RichiestaPresaInCaricoImpl extends DataItemImpl<Integer> implements RichiestaPresaInCarico {

    private Integer id;
    private RichiestaAcquisto richiestaAcquisto;
    private TecnicoPreventivi tecnicoPreventivi;

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

    public TecnicoPreventivi getTecnicoPreventivi() {
        return tecnicoPreventivi;
    }

    public void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) {
        this.tecnicoPreventivi = tecnicoPreventivi;
    }
}

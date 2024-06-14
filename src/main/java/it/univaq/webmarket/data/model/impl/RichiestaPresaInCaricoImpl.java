package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class RichiestaPresaInCaricoImpl extends DataItemImpl<Integer> implements RichiestaPresaInCarico {

    private Richiesta richiesta;
    private TecnicoPreventivi tecnicoPreventivi;

    public RichiestaPresaInCaricoImpl() {
        super();
        this.richiesta = null;
        this.tecnicoPreventivi = null;
    }

    @Override
    public Richiesta getRichiesta() {
        return richiesta;
    }

    @Override
    public void setRichiesta(Richiesta richiesta) {
        this.richiesta = richiesta;
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

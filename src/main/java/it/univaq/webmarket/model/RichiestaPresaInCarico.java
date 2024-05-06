package it.univaq.webmarket.model;

import it.univaq.webmarket.model.utenti.TecnicoPreventivi;

public class RichiestaPresaInCarico {

    private Integer id;
    private RichiestaAcquisto richiestaAcquisto;
    private TecnicoPreventivi tecnicoPreventivi;

    public RichiestaPresaInCarico(Integer id, RichiestaAcquisto richiestaAcquisto, TecnicoPreventivi tecnicoPreventivi) {
        this.id = id;
        this.richiestaAcquisto = richiestaAcquisto;
        this.tecnicoPreventivi = tecnicoPreventivi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RichiestaAcquisto getRichiestaAcquisto() {
        return richiestaAcquisto;
    }

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

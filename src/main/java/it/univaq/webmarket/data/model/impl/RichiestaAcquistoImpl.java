package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDateTime;

public class RichiestaAcquistoImpl extends DataItemImpl<Integer> implements RichiestaAcquisto {

    private String note;
    private String codiceRichiesta;
    private LocalDateTime dataEOra;

    public RichiestaAcquistoImpl() {
        super();
        this.note = "";
        this.codiceRichiesta = "";
        this.dataEOra = null;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public void setNote(String note) { this.note = note;}

    @Override
    public String getCodiceRichiesta() {
        return this.codiceRichiesta;
    }

    @Override
    public void setCodiceRichiesta(String codiceRichiesta) { this.codiceRichiesta = codiceRichiesta; }

    @Override
    public LocalDateTime getDataEOra() {
        return this.dataEOra;
    }

    @Override
    public void setDataEOra(LocalDateTime dataEOra) { this.dataEOra = dataEOra; }
}

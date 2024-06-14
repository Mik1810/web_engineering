package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDateTime;

public class RichiestaImpl extends DataItemImpl<Integer> implements Richiesta {

    private String note;
    private String codiceRichiesta;
    private LocalDateTime dataEOra;
    private Ordinante ordinante;

    public RichiestaImpl() {
        super();
        this.note = "";
        this.codiceRichiesta = "";
        this.dataEOra = null;
        this.ordinante = null;
    }

    @Override
    public Ordinante getOrdinante() { return this.ordinante; }

    @Override
    public void setOrdinante(Ordinante ordinante) {
        this.ordinante = ordinante;
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

    @Override
    public String toString() {
        return "RichiestaImpl{" +
                "note='" + note + '\'' +
                ", codiceRichiesta='" + codiceRichiesta + '\'' +
                ", dataEOra=" + dataEOra +
                ", ordinante=" + ordinante +
                '}';
    }
}

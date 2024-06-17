package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDate;
import java.util.List;

public class RichiestaImpl extends DataItemImpl<Integer> implements Richiesta {

    private String note;
    private String codiceRichiesta;
    private LocalDate data;
    private Ordinante ordinante;
    private List<CaratteristicaConValore> caratteristicheConValore;

    public RichiestaImpl() {
        super();
        this.note = "";
        this.codiceRichiesta = "";
        this.data = null;
        this.ordinante = null;
        this.caratteristicheConValore = null;
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
    public LocalDate getData() {
        return this.data;
    }

    @Override
    public void setData(LocalDate data) { this.data = data; }

    @Override
    public List<CaratteristicaConValore> getCaratteristicheConValore() {
        return this.caratteristicheConValore;
    }

    @Override
    public void setCaratteristicheConValore(List<CaratteristicaConValore> caratteristicheConValore) {
        this.caratteristicheConValore = caratteristicheConValore;
    }

    @Override
    public String toString() {
        return "RichiestaImpl{" +
                "note='" + note + '\'' +
                ", codiceRichiesta='" + codiceRichiesta + '\'' +
                ", data=" + data +
                ", ordinante=" + ordinante +
                ", caratteristicaConValore=" + caratteristicheConValore +
                '}';
    }
}

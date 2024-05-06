package it.univaq.webmarket.model;

import it.univaq.webmarket.model.utenti.Ordinante;

import java.util.List;

public class StoricoOrdini {

    private Integer id;
    private Ordinante ordinante;
    private List<OrdineAcquisto> ordini;

    public StoricoOrdini(Integer id, Ordinante ordinante, List<OrdineAcquisto> ordini) {
        this.id = id;
        this.ordinante = ordinante;
        this.ordini = ordini;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ordinante getOrdinante() {
        return ordinante;
    }

    public void setOrdinante(Ordinante ordinante) {
        this.ordinante = ordinante;
    }

    public List<OrdineAcquisto> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<OrdineAcquisto> ordini) {
        this.ordini = ordini;
    }
}

package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.data.model.StoricoOrdini;
import it.univaq.webmarket.data.model.impl.utenti.OrdinanteImpl;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.util.List;

public class StoricoOrdiniImpl extends DataItemImpl<Integer> implements StoricoOrdini {

    private Integer id;
    private Ordinante ordinante;
    private List<OrdineAcquisto> ordini;

    public StoricoOrdiniImpl() {
        this.id = null;
        this.ordinante = null;
        this.ordini = null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Ordinante getOrdinante() {
        return ordinante;
    }

    public void setOrdinante(Ordinante ordinante) {
        this.ordinante = ordinante;
    }

    @Override
    public List<OrdineAcquisto> getOrdini() {
        return ordini;
    }

    @Override
    public void setOrdini(List<OrdineAcquisto> ordini) {
        this.ordini = ordini;
    }
}

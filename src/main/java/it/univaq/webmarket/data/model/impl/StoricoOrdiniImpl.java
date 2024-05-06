package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.StoricoOrdini;
import it.univaq.webmarket.data.model.impl.utenti.OrdinanteImpl;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.util.List;

public class StoricoOrdiniImpl extends DataItemImpl<Integer> implements StoricoOrdini {

    private Integer id;
    private OrdinanteImpl ordinanteImpl;
    private List<OrdineAcquistoimpl> ordini;

    public StoricoOrdiniImpl() {
        this.id = null;
        this.ordinanteImpl = null;
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

    public OrdinanteImpl getOrdinanteImpl() {
        return ordinanteImpl;
    }

    public void setOrdinanteImpl(OrdinanteImpl ordinanteImpl) {
        this.ordinanteImpl = ordinanteImpl;
    }

    @Override
    public List<OrdineAcquistoimpl> getOrdini() {
        return ordini;
    }

    @Override
    public void setOrdini(List<OrdineAcquistoimpl> ordini) {
        this.ordini = ordini;
    }
}

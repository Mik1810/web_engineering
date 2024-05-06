package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoOrdiniImpl;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class OrdineAcquistoimpl extends DataItemImpl<Integer> implements OrdineAcquisto {

    private Integer id;
    private StatoConsegna statoConsegna;
    private Feedback feedback;
    private TecnicoOrdiniImpl tecnicoOrdiniImpl;
    private PropostaImpl propostaImpl;

    public OrdineAcquistoimpl() {
        this.id = null;
        this.statoConsegna = null;
        this.feedback = null;
        this.tecnicoOrdiniImpl = null;
        this.propostaImpl = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatoConsegna getStatoConsegna() {
        return statoConsegna;
    }

    public void setStatoConsegna(StatoConsegna statoConsegna) {
        this.statoConsegna = statoConsegna;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public TecnicoOrdiniImpl getTecnicoOrdini() {
        return tecnicoOrdiniImpl;
    }

    public void setTecnicoOrdini(TecnicoOrdiniImpl tecnicoOrdiniImpl) {
        this.tecnicoOrdiniImpl = tecnicoOrdiniImpl;
    }

    public PropostaImpl getProposta() {
        return propostaImpl;
    }

    public void setProposta(PropostaImpl propostaImpl) {
        this.propostaImpl = propostaImpl;
    }
}

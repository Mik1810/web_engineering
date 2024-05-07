package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoOrdiniImpl;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class OrdineAcquistoimpl extends DataItemImpl<Integer> implements OrdineAcquisto {

    private Integer id;
    private StatoConsegna statoConsegna;
    private Feedback feedback;
    private TecnicoOrdini tecnicoOrdini;
    private Proposta proposta;

    public OrdineAcquistoimpl() {
        this.id = null;
        this.statoConsegna = null;
        this.feedback = null;
        this.tecnicoOrdini = null;
        this.proposta = null;
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

    public TecnicoOrdini getTecnicoOrdini() {
        return tecnicoOrdini;
    }

    public void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini) {
        this.tecnicoOrdini = tecnicoOrdini;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
}

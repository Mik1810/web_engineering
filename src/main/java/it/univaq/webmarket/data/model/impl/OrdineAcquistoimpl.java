package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.OrdineAcquisto;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class OrdineAcquistoimpl extends DataItemImpl<Integer> implements OrdineAcquisto {

    private StatoConsegna statoConsegna;
    private Feedback feedback;
    private TecnicoOrdini tecnicoOrdini;
    private Proposta proposta;

    public OrdineAcquistoimpl() {
        super();
        this.statoConsegna = null;
        this.feedback = null;
        this.tecnicoOrdini = null;
        this.proposta = null;
    }

    @Override
    public StatoConsegna getStatoConsegna() {
        return statoConsegna;
    }

    @Override
    public void setStatoConsegna(StatoConsegna statoConsegna) {
        this.statoConsegna = statoConsegna;
    }

    @Override
    public Feedback getFeedback() {
        return feedback;
    }

    @Override
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public TecnicoOrdini getTecnicoOrdini() {
        return tecnicoOrdini;
    }

    @Override
    public void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini) {
        this.tecnicoOrdini = tecnicoOrdini;
    }

    @Override
    public Proposta getProposta() {
        return proposta;
    }

    @Override
    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }
}

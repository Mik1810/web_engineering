package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDateTime;

public class OrdineImpl extends DataItemImpl<Integer> implements Ordine {

    private StatoConsegna statoConsegna;
    private Feedback feedback;

    private LocalDateTime dataConsegna;
    private TecnicoOrdini tecnicoOrdini;
    private Proposta proposta;

    public OrdineImpl() {
        super();
        this.statoConsegna = null;
        this.feedback = null;
        this.tecnicoOrdini = null;
        this.proposta = null;
        this.dataConsegna = null;
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

    @Override
    public LocalDateTime getDataConsegna() {
        return dataConsegna;
    }

    @Override
    public void setDataConsegna(LocalDateTime dataConsegna) {
        this.dataConsegna = dataConsegna;
    }
}

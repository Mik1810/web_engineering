package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataItemImpl;

import java.time.LocalDate;

public class OrdineImpl extends DataItemImpl<Integer> implements Ordine {

    private Integer statoConsegna, feedback;
    private LocalDate dataConsegna;
    private TecnicoOrdini tecnicoOrdini;
    private Proposta proposta;

    public OrdineImpl() {
        super();
        this.statoConsegna = 0;
        this.feedback = 0;
        this.tecnicoOrdini = null;
        this.proposta = null;
        this.dataConsegna = null;
    }

    @Override
    public Integer getStatoConsegna() {
        return statoConsegna;
    }

    @Override
    public void setStatoConsegna(Integer statoConsegna) {
        this.statoConsegna = statoConsegna;
    }

    @Override
    public Integer getFeedback() {
        return feedback;
    }

    @Override
    public void setFeedback(Integer feedback) {
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
    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    @Override
    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    @Override
    public String toString() {
        return "OrdineImpl{" +
                "statoConsegna=" + statoConsegna +
                ", feedback=" + feedback +
                ", dataConsegna=" + dataConsegna +
                ", tecnicoOrdini=" + tecnicoOrdini +
                ", proposta=" + proposta +
                '}';
    }
}

package it.univaq.webmarket.model;

import it.univaq.webmarket.model.utenti.TecnicoOrdini;
import it.univaq.webmarket.model.enums.Feedback;
import it.univaq.webmarket.model.enums.StatoConsegna;

public class OrdineAcquisto {

    private Integer id;
    private StatoConsegna statoConsegna;
    private Feedback feedback;
    private TecnicoOrdini tecnicoOrdini;
    private Proposta proposta;

    public OrdineAcquisto(Integer id, StatoConsegna statoConsegna, Feedback feedback, TecnicoOrdini tecnicoOrdini, Proposta proposta) {
        this.id = id;
        this.statoConsegna = statoConsegna;
        this.feedback = feedback;
        this.tecnicoOrdini = tecnicoOrdini;
        this.proposta = proposta;
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

package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.PropostaImpl;
import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoOrdiniImpl;

public interface OrdineAcquisto {
    public Integer getId();

    void setId(Integer id);

    StatoConsegna getStatoConsegna();

    void setStatoConsegna(StatoConsegna statoConsegna);

    Feedback getFeedback();

    void setFeedback(Feedback feedback);

    TecnicoOrdini getTecnicoOrdini();

    void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini);

    Proposta getProposta();

    void setProposta(Proposta proposta);


}

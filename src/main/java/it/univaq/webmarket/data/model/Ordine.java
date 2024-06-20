package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.enums.Feedback;
import it.univaq.webmarket.data.model.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDate;

public interface Ordine extends DataItem<Integer> {

    StatoConsegna getStatoConsegna();

    void setStatoConsegna(StatoConsegna statoConsegna);

    Feedback getFeedback();

    void setFeedback(Feedback feedback);

    TecnicoOrdini getTecnicoOrdini();

    void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini);

    Proposta getProposta();

    void setProposta(Proposta proposta);

    LocalDate getDataConsegna();

    void setDataConsegna(LocalDate dataConsegna);


}

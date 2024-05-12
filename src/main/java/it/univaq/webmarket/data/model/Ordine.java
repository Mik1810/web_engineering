package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.enums.Feedback;
import it.univaq.webmarket.data.model.impl.enums.StatoConsegna;
import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDateTime;

public interface Ordine extends DataItem<Integer> {

    StatoConsegna getStatoConsegna();

    void setStatoConsegna(StatoConsegna statoConsegna);

    Feedback getFeedback();

    void setFeedback(Feedback feedback);

    TecnicoOrdini getTecnicoOrdini();

    void setTecnicoOrdini(TecnicoOrdini tecnicoOrdini);

    Proposta getProposta();

    void setProposta(Proposta proposta);

    LocalDateTime getDataConsegna();

    void setDataConsegna(LocalDateTime dataConsegna);


}

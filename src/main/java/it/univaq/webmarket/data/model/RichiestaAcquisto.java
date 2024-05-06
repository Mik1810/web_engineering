package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDateTime;

public interface RichiestaAcquisto extends DataItem<Integer> {

    String getNote();

    void setNote(String note);

    String getCodiceRichiesta();

    void setCodiceRichiesta(String codiceRichiesta);

    LocalDateTime getDataEOra();

    void setDataEOra(LocalDateTime dataEOra);


}

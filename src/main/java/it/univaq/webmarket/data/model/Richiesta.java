package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

import java.time.LocalDate;
import java.util.List;

public interface Richiesta extends DataItem<Integer> {

    Ordinante getOrdinante();

    void setOrdinante(Ordinante ordinante);

    String getNote();

    void setNote(String note);

    String getCodiceRichiesta();

    void setCodiceRichiesta(String codiceRichiesta);

    LocalDate getData();

    void setData(LocalDate dataEOra);

    List<CaratteristicaConValore> getCaratteristicheConValore();

    void setCaratteristicheConValore(List<CaratteristicaConValore> caratteristicheConValore);

}

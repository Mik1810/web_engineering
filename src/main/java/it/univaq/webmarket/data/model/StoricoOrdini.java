package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.OrdineAcquistoimpl;
import it.univaq.webmarket.data.model.impl.utenti.OrdinanteImpl;

import java.util.List;

public interface StoricoOrdini {

    Integer getId();

    void setId(Integer id);

    Ordinante getOrdinante();

    void setOrdinante(Ordinante ordinante);

    List<OrdineAcquisto> getOrdini();

    void setOrdini(List<OrdineAcquisto> ordini);
}

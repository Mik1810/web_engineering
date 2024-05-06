package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.OrdineAcquistoimpl;
import it.univaq.webmarket.data.model.impl.utenti.OrdinanteImpl;

import java.util.List;

public interface StoricoOrdini {

    Integer getId();

    void setId(Integer id);

    OrdinanteImpl getOrdinanteImpl();

    void setOrdinanteImpl(OrdinanteImpl ordinanteImpl);

    List<OrdineAcquistoimpl> getOrdini();

    void setOrdini(List<OrdineAcquistoimpl> ordini);
}

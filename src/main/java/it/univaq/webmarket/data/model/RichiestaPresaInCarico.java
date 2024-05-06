package it.univaq.webmarket.data.model;

import it.univaq.webmarket.model.RichiestaAcquisto;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoPreventiviImpl;

public interface RichiestaPresaInCarico {


    Integer getId();

    void setId(Integer id);

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    TecnicoPreventiviImpl getTecnicoPreventivi();

    void setTecnicoPreventiviImpl(TecnicoPreventiviImpl tecnicoPreventivi);

}

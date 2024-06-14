package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface RichiestaPresaInCarico extends DataItem<Integer> {

    Richiesta getRichiesta();

    void setRichiesta(Richiesta richiesta);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);

}

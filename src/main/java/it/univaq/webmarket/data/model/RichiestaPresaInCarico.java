package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface RichiestaPresaInCarico extends DataItem<Integer> {

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);

}

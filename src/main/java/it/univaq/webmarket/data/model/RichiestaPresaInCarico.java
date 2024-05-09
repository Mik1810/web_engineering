package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface RichiestaPresaInCarico extends DataItem<Integer> {

    RichiestaConCaratteristiche getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaConCaratteristiche richiestaAcquisto);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);

}

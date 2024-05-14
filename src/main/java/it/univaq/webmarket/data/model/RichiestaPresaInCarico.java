package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface RichiestaPresaInCarico extends DataItem<Integer> {

    RichiestaConCaratteristiche getRichiestaConCaratteristiche();

    void setRichiestaConCaratteristiche(RichiestaConCaratteristiche richiestaConCaratteristiche);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);

}

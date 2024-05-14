package it.univaq.webmarket.data.model;


import it.univaq.webmarket.framework.data.DataItem;

import java.util.Map;

public interface RichiestaConCaratteristiche extends DataItem<Integer> {

    Richiesta getRichiesta();

    void setRichiesta(Richiesta richiesta);

    Map<Caratteristica, String> getCaratteristiche();

    void setCaratteristiche(Map<Caratteristica, String> caratteristiche);

}

package it.univaq.webmarket.data.model;


import java.util.Map;

public interface RichiestaConCaratteristiche {

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    Map<Caratteristica, String> getCaratteristiche();

    void setCaratteristiche(Map<Caratteristica, String> caratteristiche);

}

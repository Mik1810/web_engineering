package it.univaq.webmarket.data.model;


import java.util.Map;

public interface RichiestaConCaratteristiche {

    Richiesta getRichiestaAcquisto();

    void setRichiestaAcquisto(Richiesta richiestaAcquisto);

    Map<Caratteristica, String> getCaratteristiche();

    void setCaratteristiche(Map<Caratteristica, String> caratteristiche);

}

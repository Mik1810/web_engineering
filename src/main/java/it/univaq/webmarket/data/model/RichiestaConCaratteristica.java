package it.univaq.webmarket.data.model;


public interface RichiestaConCaratteristica {

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    Caratteristica getCaratteristica();

    void setCaratteristica(Caratteristica caratteristica);

    String getValore();

    void setValore(String valore);

}

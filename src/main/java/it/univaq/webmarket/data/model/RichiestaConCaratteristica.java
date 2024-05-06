package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.CaratteristicaImpl;
import it.univaq.webmarket.model.RichiestaAcquisto;

public interface RichiestaConCaratteristica {


    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    CaratteristicaImpl getCaratteristica();

    void setCaratteristica(CaratteristicaImpl caratteristica);

    String getValore();

    void setValore(String valore);

}

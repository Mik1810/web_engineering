package it.univaq.webmarket.data.model;
import it.univaq.webmarket.data.model.impl.utenti.TecnicoPreventiviImpl;

public interface RichiestaPresaInCarico {


    Integer getId();

    void setId(Integer id);

    RichiestaAcquisto getRichiestaAcquisto();

    void setRichiestaAcquisto(RichiestaAcquisto richiestaAcquisto);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);

}

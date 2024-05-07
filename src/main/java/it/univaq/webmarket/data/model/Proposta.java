package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.enums.StatoProposta;
import it.univaq.webmarket.framework.data.DataItem;

public interface Proposta extends DataItem<Integer> {

    String getCodiceProdotto();

    void setCodiceProdotto(String codiceProdotto);

    String getProduttore();

    void setProduttore(String produttore);

    String getNote();

    void setNote(String note);

    Float getPrezzo();

    void setPrezzo(Float prezzo);

    String getNomeProdotto();

    void setNomeProdotto(String nomeProdotto);

    StatoProposta getStatoProposta();

    void setStatoProposta(StatoProposta statoProposta);

    String getMotivazione();

    void setMotivazione(String motivazione);

    TecnicoPreventivi getTecnicoPreventivi();

    void setTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi);
}

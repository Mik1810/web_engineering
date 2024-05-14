package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface Ufficio extends DataItem<Integer> {

    String getSede();

    String getNumeroTelefono();

    Integer getPiano();

    Integer getNumeroUfficio();

    String getCitta();

    void setSede(String sede);

    void setNumeroTelefono(String numeroTelefono);

    void setNumeroUfficio(Integer numeroUfficio);

    void setPiano(Integer piano);

    void setCitta(String citta);
}

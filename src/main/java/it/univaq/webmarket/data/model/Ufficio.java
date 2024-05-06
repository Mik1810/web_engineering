package it.univaq.webmarket.data.model;

public interface Ufficio {
    Integer getId();

    String getSede();

    String getNumeroTelefono();

    Integer getPiano();

    Integer getNumeroUfficio();

    void setId(Integer id);

    void setSede(String sede);

    void setNumeroTelefono(String numeroTelefono);

    void setNumeroUfficio(Integer numeroUfficio);

    void setPiano(Integer piano);
}

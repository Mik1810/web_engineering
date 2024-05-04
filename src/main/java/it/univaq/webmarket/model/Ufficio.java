package it.univaq.webmarket.model;

public class Ufficio {

    private Integer id;
    private String sede;
    private String numeroTelefono;
    private Integer numeroUfficio;
    private Integer piano;

    public Ufficio(Integer id, String sede, String numeroTelefono, Integer piano) {
        this.id = id;
        this.sede = sede;
        this.numeroTelefono = numeroTelefono;
        this.piano = piano;
    }

    public Integer getId() {
        return id;
    }

    public String getSede() {
        return sede;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public Integer getPiano() {
        return piano;
    }

    public Integer getNumeroUfficio() {
        return numeroUfficio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setNumeroUfficio(Integer numeroUfficio) {
        this.numeroUfficio = numeroUfficio;
    }

    public void setPiano(Integer piano) {
        this.piano = piano;
    }
}

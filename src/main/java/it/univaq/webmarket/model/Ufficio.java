package it.univaq.webmarket.model;

public class Ufficio {

    private Integer id;
    private String sede;
    private int numeroTelefono;
    private int numeroUfficio;
    private int piano;

    public Ufficio(Integer id, String sede, int numeroTelefono, int piano) {
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

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public int getPiano() {
        return piano;
    }

    public int getNumeroUfficio() {
        return numeroUfficio;
    }

}

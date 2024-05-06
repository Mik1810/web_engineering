package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class UfficioImpl extends DataItemImpl<Integer> implements Ufficio{

    private Integer id;
    private String sede;
    private String numeroTelefono;
    private Integer numeroUfficio;
    private Integer piano;

    public UfficioImpl() {
        this.id = null;
        this.sede = "";
        this.numeroTelefono = "";
        this.numeroUfficio = null;
        this.piano = null;
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

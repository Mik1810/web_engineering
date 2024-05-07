package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class UfficioImpl extends DataItemImpl<Integer> implements Ufficio{

    private String sede;
    private String numeroTelefono;
    private Integer numeroUfficio;
    private Integer piano;

    public UfficioImpl() {
        super();
        this.sede = "";
        this.numeroTelefono = "";
        this.numeroUfficio = null;
        this.piano = null;
    }

    @Override
    public String getSede() {
        return sede;
    }

    @Override
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    @Override
    public Integer getPiano() {
        return piano;
    }

    @Override
    public Integer getNumeroUfficio() {
        return numeroUfficio;
    }

    @Override
    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void setNumeroUfficio(Integer numeroUfficio) {
        this.numeroUfficio = numeroUfficio;
    }

    @Override
    public void setPiano(Integer piano) {
        this.piano = piano;
    }
}

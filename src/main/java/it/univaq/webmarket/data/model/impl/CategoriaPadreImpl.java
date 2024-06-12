package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CategoriaPadreImpl extends DataItemImpl<Integer> implements CategoriaPadre {

    private String nome;

    public CategoriaPadreImpl() {
        super();
        this.nome = "";
    }

    @Override
    public String getNome() { return this.nome;}

    @Override
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "key = " + getKey() +
                ", nome = " + getNome() +
                "}";
    }
}

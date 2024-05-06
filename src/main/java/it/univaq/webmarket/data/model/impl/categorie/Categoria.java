package it.univaq.webmarket.data.model.impl.categorie;

import it.univaq.webmarket.framework.data.DataItemImpl;

public abstract class Categoria extends DataItemImpl<Integer>{

    private String nome;
    private Integer id;

    public Categoria() {
        this.nome = "";
        this.id = null;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

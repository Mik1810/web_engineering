package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CaratteristicaImpl extends DataItemImpl<Integer> implements Caratteristica {

    private Integer id;
    private String nome;
    private String unitaMisura;
    private CategoriaNipote categoriaNipote;

    public CaratteristicaImpl() {
        this.id = null;
        this.nome = "";
        this.unitaMisura = "";
        this.categoriaNipote = null;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public CategoriaNipote getCategoriaNipote() { return categoriaNipote; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public void setCategoriaNipote(CategoriaNipote categoriaNipote) {
        this.categoriaNipote = categoriaNipote;
    }
}

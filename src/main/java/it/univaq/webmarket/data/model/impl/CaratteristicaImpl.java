package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CaratteristicaImpl extends DataItemImpl<Integer> implements Caratteristica {

    private String nome;
    private String unitaMisura;
    private CategoriaNipote categoriaNipote;

    public CaratteristicaImpl() {
        super();
        this.nome = "";
        this.unitaMisura = "";
        this.categoriaNipote = null;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getUnitaMisura() {
        return unitaMisura;
    }

    @Override
    public CategoriaNipote getCategoriaNipote() { return categoriaNipote; }

    @Override
    public void setNome(String nome) { this.nome = nome;}

    @Override
    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    @Override
    public void setCategoriaNipote(CategoriaNipote categoriaNipote) {
        this.categoriaNipote = categoriaNipote;
    }

    @Override
    public String toString() {
        return "CaratteristicaImpl{" +
                "nome='" + nome + '\'' +
                ", unitaMisura='" + unitaMisura + '\'' +
                ", categoriaNipote=" + categoriaNipote +
                '}';
    }
}

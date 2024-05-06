package it.univaq.webmarket.data.model.impl;

import it.univaq.webmarket.data.model.Caratteristica;
import it.univaq.webmarket.data.model.impl.categorie.CategoriaNipoteImpl;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CaratteristicaImpl extends DataItemImpl<Integer> implements Caratteristica {

    private Integer id;
    private String nome;
    private String unitaMisura;
    private CategoriaNipoteImpl categoriaNipoteImpl;

    public CaratteristicaImpl() {
        this.id = null;
        this.nome = "";
        this.unitaMisura = "";
        this.categoriaNipoteImpl = null;
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

    public CategoriaNipoteImpl getCategoriaNipote() { return categoriaNipoteImpl; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUnitaMisura(String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    public void setCategoriaNipote(CategoriaNipoteImpl categoriaNipoteImpl) {
        this.categoriaNipoteImpl = categoriaNipoteImpl;
    }
}

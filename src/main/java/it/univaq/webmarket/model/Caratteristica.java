package it.univaq.webmarket.model;

import it.univaq.webmarket.model.categorie.CategoriaNipote;

public class Caratteristica {

    private Integer id;
    private String nome;
    private String unitaMisura;
    private CategoriaNipote categoriaNipote;

    public Caratteristica(Integer id, String nome, String unitaMisura, CategoriaNipote categoriaNipote) {
        this.id = id;
        this.nome = nome;
        this.unitaMisura = unitaMisura;
        this.categoriaNipote = categoriaNipote;
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

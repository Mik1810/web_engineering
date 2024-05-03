package it.univaq.webmarket.model;

public class Caratteristica {

    private Integer id;
    private String nome;
    private String unitaMisura;

    public Caratteristica(Integer id, String nome, String unitaMisura) {
        this.id = id;
        this.nome = nome;
        this.unitaMisura = unitaMisura;
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
}

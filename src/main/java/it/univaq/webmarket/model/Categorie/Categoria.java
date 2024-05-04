package it.univaq.webmarket.model.Categorie;

public abstract class Categoria {

    private String nome;
    private Integer id;

    public Categoria(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
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

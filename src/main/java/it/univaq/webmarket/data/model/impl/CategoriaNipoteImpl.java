package it.univaq.webmarket.data.model.impl;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CategoriaNipoteImpl extends DataItemImpl<Integer> implements CategoriaNipote {

    private CategoriaFiglio categoriaGenitore;
    private String nome;

    public CategoriaNipoteImpl() {
        super();
        this.categoriaGenitore = null;
        this.nome = "";
    }

    @Override
    public String getNome() { return this.nome; }

    @Override
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public CategoriaFiglio getCategoriaGenitore() { return this.categoriaGenitore; }

    @Override
    public void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) {
        this.categoriaGenitore = categoriaFiglio;
    }
}

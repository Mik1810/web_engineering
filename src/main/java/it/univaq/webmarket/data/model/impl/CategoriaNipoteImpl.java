package it.univaq.webmarket.data.model.impl;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;
import it.univaq.webmarket.framework.data.DataItemImpl;

public class CategoriaNipoteImpl extends DataItemImpl<Integer> implements CategoriaNipote {

    private CategoriaFiglio categoriaFiglio;
    private String nome;

    public CategoriaNipoteImpl() {
        super();
        this.categoriaFiglio = null;
        this.nome = "";
    }

    @Override
    public String getNome() { return this.nome; }

    @Override
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public CategoriaFiglio getCategoriaGenitore() { return this.categoriaFiglio; }

    @Override
    public void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) {
        this.categoriaFiglio = categoriaFiglio;
    }
}

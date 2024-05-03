package it.univaq.webmarket.model.Categorie;

public class CategoriaNipote extends Categoria{

    private CategoriaFiglio categoriaFiglio;

    public CategoriaNipote(String nome, Integer id, CategoriaFiglio categoriaFiglio) {
        super(nome, id);
        this.categoriaFiglio = categoriaFiglio;
    }
}

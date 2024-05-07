package it.univaq.webmarket.data.model.impl.categorie;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaNipote;

public class CategoriaNipoteImpl extends Categoria implements CategoriaNipote {

    private CategoriaFiglio categoriaFiglio;

    public CategoriaNipoteImpl() {
        super();
        this.categoriaFiglio = null;
    }

    public CategoriaFiglio getCategoriaGenitore() { return this.categoriaFiglio; }

    public void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) {
        this.categoriaFiglio = categoriaFiglio;
    }
}

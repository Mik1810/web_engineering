package it.univaq.webmarket.data.model.impl.categorie;

import it.univaq.webmarket.data.model.CategoriaNipote;

public class CategoriaNipoteImpl extends Categoria implements CategoriaNipote {

    private CategoriaFiglioImpl categoriaFiglioImpl;

    public CategoriaNipoteImpl() {
        super();
        this.categoriaFiglioImpl = null;
    }

    public CategoriaFiglioImpl getCategoriaGenitore() { return this.categoriaFiglioImpl; }

    public void setCategoriaGenitore(CategoriaFiglioImpl categoriaFiglioImpl) {
        this.categoriaFiglioImpl = categoriaFiglioImpl;
    }
}

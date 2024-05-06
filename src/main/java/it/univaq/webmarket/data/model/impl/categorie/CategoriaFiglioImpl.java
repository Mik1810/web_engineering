package it.univaq.webmarket.data.model.impl.categorie;

import it.univaq.webmarket.data.model.CategoriaFiglio;

public class CategoriaFiglioImpl extends Categoria implements CategoriaFiglio {

    private CategoriaPadreImpl categoriaPadreImpl;

    public CategoriaFiglioImpl() {
        super();
        this.categoriaPadreImpl = null;
    }

    public CategoriaPadreImpl getCategoriaGenitore() { return this.categoriaPadreImpl; }

    public void setCategoriaGenitore(CategoriaPadreImpl categoriaPadreImpl) {
        this.categoriaPadreImpl = categoriaPadreImpl;
    }
}

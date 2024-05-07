package it.univaq.webmarket.data.model.impl.categorie;

import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaPadre;

public class CategoriaFiglioImpl extends Categoria implements CategoriaFiglio {

    private CategoriaPadre categoriaPadre;

    public CategoriaFiglioImpl() {
        super();
        this.categoriaPadre = null;
    }

    public CategoriaPadre getCategoriaGenitore() { return this.categoriaPadre; }

    public void setCategoriaGenitore(CategoriaPadre categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
}

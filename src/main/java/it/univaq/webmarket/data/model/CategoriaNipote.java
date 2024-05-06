package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.categorie.CategoriaFiglioImpl;

public interface CategoriaNipote {

    CategoriaFiglioImpl getCategoriaGenitore();

    void setCategoriaGenitore(CategoriaFiglioImpl categoriaFiglioImpl) ;
}

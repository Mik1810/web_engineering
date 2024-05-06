package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.categorie.CategoriaPadreImpl;

public interface CategoriaFiglio {
    CategoriaPadreImpl getCategoriaGenitore() ;

    void setCategoriaGenitore(CategoriaPadreImpl categoriaPadreImpl) ;
}

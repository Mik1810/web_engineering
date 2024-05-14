package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface CategoriaNipote  extends Categoria {

    CategoriaFiglio getCategoriaGenitore();

    void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) ;
}

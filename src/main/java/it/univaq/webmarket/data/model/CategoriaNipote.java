package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface CategoriaNipote  extends DataItem<Integer> {

    String getNome();

    void setNome(String nome);

    CategoriaFiglio getCategoriaGenitore();

    void setCategoriaGenitore(CategoriaFiglio categoriaFiglio) ;
}

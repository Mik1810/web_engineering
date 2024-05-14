package it.univaq.webmarket.data.model;


import it.univaq.webmarket.framework.data.DataItem;

public interface CategoriaFiglio extends Categoria {

    CategoriaPadre getCategoriaGenitore() ;

    void setCategoriaGenitore(CategoriaPadre categoriaPadre) ;
}

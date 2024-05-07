package it.univaq.webmarket.data.model;


import it.univaq.webmarket.framework.data.DataItem;

public interface CategoriaFiglio extends DataItem<Integer> {

    String getNome();

    void setNome(String nome);

    CategoriaPadre getCategoriaGenitore() ;

    void setCategoriaGenitore(CategoriaPadre categoriaPadre) ;
}

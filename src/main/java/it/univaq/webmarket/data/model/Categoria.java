package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface Categoria extends DataItem<Integer> {

    String getNome();

    void setNome(String nome);
}

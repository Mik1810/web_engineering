package it.univaq.webmarket.data.model.enums;

import it.univaq.webmarket.framework.data.DataItem;

public interface StatoEnum extends DataItem<Integer> {

    String getNome();

    void setNome(String nome);

}

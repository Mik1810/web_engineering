package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface Caratteristica extends DataItem<Integer> {

    String getUnitaMisura();

    CategoriaNipote getCategoriaNipote();

    String getNome();

    void setNome(String nome);

    void setUnitaMisura(String unitaMisura);

    void setCategoriaNipote(CategoriaNipote categoriaNipote);

}

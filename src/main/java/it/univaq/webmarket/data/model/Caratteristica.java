package it.univaq.webmarket.data.model;

import it.univaq.webmarket.framework.data.DataItem;

public interface Caratteristica extends DataItem<Integer> {

    String getUnitaMisura();

    CategoriaNipote getCategoriaNipote() ;

    void setNome(String nome);

    void setId(Integer id) ;

    void setUnitaMisura(String unitaMisura);

    void setCategoriaNipote(CategoriaNipote categoriaNipote);

}

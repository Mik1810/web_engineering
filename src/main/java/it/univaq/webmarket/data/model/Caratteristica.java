package it.univaq.webmarket.data.model;

import it.univaq.webmarket.data.model.impl.categorie.CategoriaNipoteImpl;

public interface Caratteristica {

    Integer getId();

    String getNome();

    String getUnitaMisura();

    CategoriaNipote getCategoriaNipote() ;

    void setNome(String nome);

    void setId(Integer id) ;

    void setUnitaMisura(String unitaMisura);

    void setCategoriaNipote(CategoriaNipote categoriaNipote);

}

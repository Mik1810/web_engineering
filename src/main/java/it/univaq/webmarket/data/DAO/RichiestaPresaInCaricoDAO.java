package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.framework.data.DataException;

public interface RichiestaPresaInCaricoDAO {

    RichiestaPresaInCarico createRichiestaPresaInCarico();

    RichiestaPresaInCarico getRichiestaPresaInCarico(int key) throws DataException;

}
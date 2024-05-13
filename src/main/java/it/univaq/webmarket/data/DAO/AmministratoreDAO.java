package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Amministratore;
import it.univaq.webmarket.framework.data.DataException;

public interface AmministratoreDAO {

    //Fixing giacomix issues
    Amministratore createAmministratore();

    Amministratore getAmministratoreByEmail(String email) throws DataException;
}

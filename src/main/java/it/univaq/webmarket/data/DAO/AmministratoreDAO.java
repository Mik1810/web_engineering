package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.Amministratore;
import it.univaq.webmarket.framework.data.DataException;

public interface AmministratoreDAO {


    /**
     * Crea un oggetto di tipo Amministratore.
     *
     * @return un oggetto di tipo Amministratore.
     */
    Amministratore createAmministratore();

    /**
     * Restituisce l'oggetto Amministratore con l'email passata come parametro
     *
     * @param email email dell'amministratore
     * @return l'oggetto Amministratore con l'email passata come parametro
     */
    Amministratore getAmministratoreByEmail(String email) throws DataException;

    /**
     * Restituisce l'oggetto Amministratore con l'id passato come parametro
     *
     * @param id id dell'amministratore
     * @return l'oggetto Amministratore con l'id passato come parametro
     */
    Amministratore getAmministratoreByID(Integer id) throws DataException;
}

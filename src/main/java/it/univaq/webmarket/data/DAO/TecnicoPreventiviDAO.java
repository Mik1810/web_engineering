package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface TecnicoPreventiviDAO {

    TecnicoPreventivi createTecnicoPreventivi();

    TecnicoPreventivi getTecnicoPreventivi(int tecnicoPreventivi_key) throws DataException;

    List<TecnicoPreventivi> getAllTecnicoPreventivi() throws DataException;

    // email è dichiarato UNIQUE, abbiamo al certezza che ne esista solo uno
    TecnicoPreventivi getTecnicoPreventiviByEmail(String email) throws DataException;

    void storeTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException;

    void deleteTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException;
}


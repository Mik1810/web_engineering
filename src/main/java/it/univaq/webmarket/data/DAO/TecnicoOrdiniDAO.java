package it.univaq.webmarket.data.DAO;

import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataException;

import java.util.List;

public interface TecnicoOrdiniDAO {

    TecnicoOrdini createTecnicoOrdini();

    TecnicoOrdini getTecnicoOrdini(int tecnicoOrdini_key) throws DataException;

    List<TecnicoOrdini> getAllTecnicoOrdini() throws DataException;

    // email è dichiarato UNIQUE, abbiamo al certezza che ne esista solo uno
    TecnicoOrdini getTecnicoOrdiniByEmail(String email) throws DataException;

    void storeTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException;

    void deleteTecnicoOrdini(TecnicoOrdini tecnicoOrdini) throws DataException;
}

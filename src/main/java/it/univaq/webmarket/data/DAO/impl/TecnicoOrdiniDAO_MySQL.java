package it.univaq.webmarket.data.DAO.impl;

import it.univaq.webmarket.data.DAO.TecnicoPreventiviDAO;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DAO;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import java.util.List;

public class TecnicoOrdiniDAO_MySQL extends DAO implements TecnicoPreventiviDAO {

    //TODO: all
    public TecnicoOrdiniDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public TecnicoPreventivi createTecnicoPreventivi() {
        return null;
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventivi(int tecnicoPreventivi_key) throws DataException {
        return null;
    }

    @Override
    public List<TecnicoPreventivi> getAllTecnicoPreventivi() throws DataException {
        return List.of();
    }

    @Override
    public TecnicoPreventivi getTecnicoPreventiviByEmail(String email) throws DataException {
        return null;
    }

    @Override
    public void storeTecnicoPreventivi(TecnicoPreventivi tecnicoPreventivi) throws DataException {

    }
}

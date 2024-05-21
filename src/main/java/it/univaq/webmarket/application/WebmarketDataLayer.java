package it.univaq.webmarket.application;

import it.univaq.webmarket.data.DAO.*;
import it.univaq.webmarket.data.DAO.impl.*;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.data.model.enums.StatoEnum;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;

import javax.sql.DataSource;
import java.sql.SQLException;

public class WebmarketDataLayer extends DataLayer {

    public WebmarketDataLayer(DataSource datasource) throws SQLException {
        super(datasource);
    }

    @Override
    public void init() throws DataException {
        registerDAO(Richiesta.class, new RichiestaDAO_MySQL(this));
        registerDAO(Ordinante.class, new OrdinanteDAO_MySQL(this));
        registerDAO(Amministratore.class, new AmministratoreDAO_MySQL(this));
        registerDAO(TecnicoOrdini.class, new TecnicoOrdiniDAO_MySQL(this));
        registerDAO(TecnicoPreventivi.class, new TecnicoPreventiviDAO_MySQL(this));
        registerDAO(Ufficio.class, new UfficioDAO_MySQL(this));
        registerDAO(StatoEnum.class, new StatiEnumDAO_MySQL(this));

    }

    public RichiestaDAO getRichiestaAcquistoDAO() {
        return (RichiestaDAO) getDAO(Richiesta.class);
    }

    public OrdinanteDAO getOrdinanteDAO() {
        return (OrdinanteDAO) getDAO(Ordinante.class);
    }

    public AmministratoreDAO getAmministratoreDAO() {
        return (AmministratoreDAO) getDAO(Amministratore.class);
    }

    public TecnicoPreventiviDAO getTecnicoPreventiviDAO() {
        return (TecnicoPreventiviDAO) getDAO(TecnicoPreventivi.class);
    }

    public TecnicoOrdiniDAO getTecnicoOrdiniDAO() {
        return (TecnicoOrdiniDAO) getDAO(TecnicoOrdini.class);
    }

    public UfficioDAO getUfficioDAO() {
        return (UfficioDAO) getDAO(Ufficio.class);
    }

    public StatiEnumDAO getStatiEnumDAO() {
        return (StatiEnumDAO) getDAO(StatoEnum.class);
    }

}

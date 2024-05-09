package it.univaq.webmarket.application;

import it.univaq.webmarket.data.DAO.OrdinanteDAO;
import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.webmarket.data.DAO.impl.RichiestaAcquistoDAO_MySQL;
import it.univaq.webmarket.data.DAO.impl.OrdinanteDAO_MySQL;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
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
        registerDAO(Richiesta.class, new RichiestaAcquistoDAO_MySQL(this));
        registerDAO(Ordinante.class, new OrdinanteDAO_MySQL(this));

    }

    public RichiestaAcquistoDAO getRichiestaAcquistoDAO() {
        return (RichiestaAcquistoDAO) getDAO(Richiesta.class);
    }

    public OrdinanteDAO getOrdinanteDAO() {
        return (OrdinanteDAO) getDAO(Ordinante.class);
    }
}

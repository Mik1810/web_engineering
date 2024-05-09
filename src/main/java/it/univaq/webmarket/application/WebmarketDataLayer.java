package it.univaq.webmarket.application;

import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.webmarket.data.DAO.impl.RichiestaAcquistoDAO_MySQL;
import it.univaq.webmarket.data.model.RichiestaAcquisto;
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
        //registriamo i nostri dao
        registerDAO(RichiestaAcquisto.class, new RichiestaAcquistoDAO_MySQL(this));
    }

    public RichiestaAcquistoDAO getRichiestaAcquistoDAO() {
        return (RichiestaAcquistoDAO) getDAO(RichiestaAcquisto.class);
    }
}

package it.univaq.webmarket.application;

import it.univaq.webmarket.framework.controller.AbstractBaseController;
import it.univaq.webmarket.framework.data.DataLayer;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 *
 * @author Giuseppe Della Penna
 */
public abstract class ApplicationBaseController extends AbstractBaseController {

    @Override
    protected DataLayer createDataLayer(DataSource ds) throws ServletException {
        try {
            return new WebmarketDataLayer(ds);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

}

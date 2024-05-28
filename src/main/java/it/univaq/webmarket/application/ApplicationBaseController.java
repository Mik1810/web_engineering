package it.univaq.webmarket.application;

import it.univaq.webmarket.framework.controller.AbstractBaseController;
import it.univaq.webmarket.framework.data.DataLayer;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giuseppe Della Penna
 */
public abstract class ApplicationBaseController extends AbstractBaseController {

    protected List<Ruolo> ruoliAutorizzati;

    @Override
    protected DataLayer createDataLayer(DataSource ds) throws ServletException {
        try {
            return new WebmarketDataLayer(ds);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void processBaseRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        if(ruoliAutorizzati != null && session != null && session.getAttribute("role") != null) {
            Ruolo ruolo = Ruolo.valueOf((String) session.getAttribute("role"));
            if (!this.ruoliAutorizzati.contains(ruolo)) {
                handleError("Utente non autorizzato!", request, response);
                return;
            }
        }
        super.processBaseRequest(request, response);
    }
}

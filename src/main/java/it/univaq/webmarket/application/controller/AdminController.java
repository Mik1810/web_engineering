package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.AmministratoreDAO;
import it.univaq.webmarket.data.model.Amministratore;
import it.univaq.webmarket.data.model.impl.proxy.AmministratoreProxy;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends ApplicationBaseController {

    protected void renderAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateManagerException {
        try {
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            HttpSession session = SecurityHelpers.checkSession(request);

            if (session == null) throw new ServletException("Sessione invalida");

            // Controllo per verificare che nessun utente che non sia amministratore possa
            // accedere alla pagina
            if (!Ruolo.AMMINISTRATORE.equals(Ruolo.valueOf((String) session.getAttribute("role")))) {
                throw new ServletException("Utente non autorizzato");
            }

            String amministratoreEmail = (String) session.getAttribute("email");

            if (amministratoreEmail == null) throw new ServletException("Email non valida");

            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            AmministratoreDAO amministratoreDAO = dl.getAmministratoreDAO();
            Amministratore amministratore = amministratoreDAO.getAmministratoreByEmail(amministratoreEmail);
            datamodel.put("email", amministratore.getEmail());
            result.activate("admin.ftl", datamodel, response);

        } catch (DataException e) {
            handleError(e, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            renderAdminPage(request, response);
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.AmministratoreDAO;
import it.univaq.webmarket.data.model.impl.proxy.AmministratoreProxy;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends ApplicationBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        HttpSession session = SecurityHelpers.checkSession(request);

        if (session != null) {
            Integer amministratoreID = (Integer) session.getAttribute("userid");
            if (amministratoreID != null) {
                WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
                try {
                    AmministratoreDAO amministratoreDAO = dl.getAmministratoreDAO();
                    AmministratoreProxy amministratore = (AmministratoreProxy) amministratoreDAO.getAmministratoreByID(amministratoreID);
                    datamodel.put("email", amministratore.getEmail());
                } catch (DataException e) {
                    System.out.println("Errore nel recupero dell'amministratore");
                }
            }
        } else { // Se la sessione è scaduta
            System.out.println("Sessione scaduta per l'admin");
        }
        try {
            result.activate("admin.ftl", datamodel, response);
        } catch (TemplateManagerException e) {
            handleError(e, request, response);
        }
    }
}

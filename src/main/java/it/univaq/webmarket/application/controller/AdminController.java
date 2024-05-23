package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.AmministratoreDAO;
import it.univaq.webmarket.data.model.impl.proxy.AmministratoreProxy;
import it.univaq.webmarket.framework.result.TemplateResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AdminController extends ApplicationBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        HttpSession session = request.getSession(false);
        if (session != null) {
            Integer amministratoreID = (Integer) session.getAttribute("userid");
            System.out.println("Amministratore ID: " + amministratoreID);
            if (amministratoreID != null) {
                WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
                AmministratoreProxy amministratore = (AmministratoreProxy) dl.getAmministratoreDAO().getAmministratoreByID(amministratoreID);
                datamodel.put("email", amministratore.getEmail());
                System.out.println(datamodel);
            }
        }
        result.activate("admin.ftl", datamodel, response);
    }
}

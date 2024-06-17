package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class GestioneStatiController extends ApplicationBaseController {

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());

            Map<String, Object> datamodel = new HashMap<>();
            datamodel.put("statiConsegna", dl.getStatiEnumDAO().getAllStatiProposta());
            datamodel.put("statiProposta", dl.getStatiEnumDAO().getAllStatiConsegna());
            datamodel.put("feedbacks", dl.getStatiEnumDAO().getAllFeedback());

            result.activate("gestione_stati.ftl", datamodel, request, response);
        } catch (DataException | TemplateManagerException e) {
            handleError(e, request, response);
        }
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        renderTemplate(request, response);
    }
}

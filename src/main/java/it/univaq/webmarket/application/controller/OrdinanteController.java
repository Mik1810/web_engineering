package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdinanteController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.ORDINANTE);
    }

    protected void renderOrdinantePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        result.activate("ordinante.ftl", datamodel, request, response);
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            renderOrdinantePage(request, response);
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestioneRichiesteOrdinante extends ApplicationBaseController {

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            Map<String, String[]> parameterMap = request.getParameterMap();

            try {
                if (parameterMap.containsKey("id")) {
                    datamodel.put("richieste", List.of(dl.getRichiestaDAO()
                            .getRichiesta(Integer.parseInt(parameterMap.get("id")[0]))));
                } else {
                    HttpSession session = SecurityHelpers.checkSession(request);
                    Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail(String.valueOf(session.getAttribute("email")));

                    if (parameterMap.containsKey("page")) {
                        Integer page = Integer.parseInt(parameterMap.get("page")[0]);

                        datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteByOrdinante(ordinante, page));
                        datamodel.put("page", page);
                    } else {
                        datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteByOrdinante(ordinante));
                        datamodel.put("page", 0);
                    }
                }
            } catch (DataException e) {
                handleError(e, request, response);
            }

            result.activate("gestione_richieste_ordinante.ftl", datamodel, request, response);
        } catch (TemplateManagerException e){
            handleError(e, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (request.getParameter("render") != null) {
                //Se l'utente richiede qualche elemento non renderizzato

                if ("Modifica".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per la modifica

                } else if ("Aggiungi".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per l'aggiunta
                } else renderTemplate(request, response);

            } else if (request.getParameter("action") != null) {
                // Se l'utente richiede un'azione

                if ("Modifica".equals(request.getParameter("action"))) {

                    // Se devo effettuare la modifica
                } else if ("Elimina".equals(request.getParameter("action"))) {

                    // Se devo effettuare l'eliminazione
                } else if ("Aggiungi".equals(request.getParameter("action"))) {

                    // Se devo effettuare l'aggiunta
                } else if ("Annulla".equals(request.getParameter("action"))) {
                    // Se voglio annullare l'azione
                    response.sendRedirect("richieste_ordinante");
                } else renderTemplate(request, response);
            } else {
                renderTemplate(request, response);
            }
        } catch (IOException ex) {
            handleError(ex, request, response);
        }
    }

}

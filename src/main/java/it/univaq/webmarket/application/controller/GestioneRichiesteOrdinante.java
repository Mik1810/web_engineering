package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestioneRichiesteOrdinante extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.ORDINANTE);
    }

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

    private void renderModify(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            datamodel.put("visibilityModify", "flex");

            if (parameterMap.containsKey("id")) {

                datamodel.put("url_has_id", true);
                datamodel.put("richieste", List.of(dl.getRichiestaDAO()
                        .getRichiesta(Integer.parseInt(parameterMap.get("id")[0]))));
                datamodel.put("richiestaDaModificare", dl.getRichiestaDAO()
                        .getRichiesta(Integer.parseInt(parameterMap.get("id")[0])));
            } else {
                HttpSession session = SecurityHelpers.checkSession(request);
                Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail(String.valueOf(session.getAttribute("email")));

                datamodel.put("page", parameterMap.get("page")[0]);
                datamodel.put("richieste", dl.getRichiestaDAO()
                        .getRichiesteByOrdinante(ordinante));
            }


            result.activate("gestione_richieste_ordinante.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }

    }

    private void handleModify(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            int richiestaId = Integer.parseInt(parameterMap.get("id")[0]);
            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);
            String note = parameterMap.containsKey("note") ? parameterMap.get("note")[0] : "";
            if (!note.equals(richiesta.getNote())) {
                richiesta.setNote(note);
                datamodel.put("success", "1");
            } else {
                datamodel.put("success", "-1");
            }
            datamodel.put("richieste", List.of(richiesta));
            dl.getRichiestaDAO().storeRichiesta(richiesta);

            result.activate("gestione_richieste_ordinante.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            int richiestaId = Integer.parseInt(parameterMap.get("id")[0]);

            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);
            Ordinante ordinante = richiesta.getOrdinante();
            dl.getRichiestaDAO().deleteRichiesta(richiesta);
            datamodel.put("success", "2");
            datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteByOrdinante(ordinante));

            result.activate("gestione_richieste_ordinante.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();

            if (parameterMap.containsKey("render")) {
                //Se l'utente richiede qualche elemento non renderizzato
                if ("Modifica".equals(parameterMap.get("render")[0])) {
                    //Se devo renderizzare il menù per la modifica
                    renderModify(request, response);
                } else renderTemplate(request, response);
            } else if (parameterMap.containsKey("action")) {
                // Se l'utente richiede un'azione
                if ("Modifica".equals(parameterMap.get("action")[0])) {
                    // Se devo effettuare la modifica
                    handleModify(request, response);
                } else if ("Elimina".equals(request.getParameter("action"))) {
                    // Se devo effettuare l'eliminazione
                    handleDelete(request, response);
                } else renderTemplate(request, response);
            } else {
                renderTemplate(request, response);
            }
        } catch (TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }

}

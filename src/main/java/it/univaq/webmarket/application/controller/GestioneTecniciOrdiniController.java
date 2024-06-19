package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.framework.data.DataException;
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

public class GestioneTecniciOrdiniController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.AMMINISTRATORE);
    }

    private void renderGestioneTecniciOrdiniPage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("gestione_tecnici_ordini.ftl", datamodel, request, response);
    }

    private void renderModify(HttpServletRequest request, HttpServletResponse response, Integer tecnico_key) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TecnicoOrdini tecnico = dl.getTecnicoOrdiniDAO().getTecnicoOrdini(tecnico_key);
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            datamodel.put("tecnicoModifica", tecnico);
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(0));
                datamodel.put("page", 0);
            }
            datamodel.put("visibilityUpdate", "flex");
            result.activate("gestione_tecnici_ordini.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response, Integer tecnico_key) {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TecnicoOrdini tecnico = dl.getTecnicoOrdiniDAO().getTecnicoOrdini(tecnico_key);
            dl.getTecnicoOrdiniDAO().deleteTecnicoOrdini(tecnico);
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                response.sendRedirect("gestione_tecnici_ordini?page=" + page);
            } else response.sendRedirect("gestione_tecnici_ordini?page=0");
        } catch (IOException | DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleModify(HttpServletRequest request, HttpServletResponse response, Integer tecnico_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TecnicoOrdini tecnico = dl.getTecnicoOrdiniDAO().getTecnicoOrdini(tecnico_key);
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
                tecnico.setEmail(request.getParameter("nome"));
                tecnico.setPassword(request.getParameter("password"));
            } else {
                datamodel.put("success", "-1");
                return;
            }


            dl.getTecnicoOrdiniDAO().storeTecnicoOrdini(tecnico);

            try {
                if (request.getParameter("page") != null) {
                    Integer page = Integer.parseInt(request.getParameter("page"));
                    datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(page));
                    datamodel.put("page", page);
                } else {
                    datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(0));
                    datamodel.put("page", 0);
                }
                datamodel.put("success", "1");

            } catch (DataException e) {
                handleError(e, request, response);
            }

            result.activate("gestione_tecnici_ordini.ftl", datamodel, request, response);

        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void renderInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(0));
                datamodel.put("page", 0);
            }
            datamodel.put("visibilityInsert", "flex");

            result.activate("gestione_tecnici_ordini.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            TecnicoOrdini tecnico = dl.getTecnicoOrdiniDAO().createTecnicoOrdini();

            if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
                tecnico.setEmail(request.getParameter("nome"));
                tecnico.setPassword(request.getParameter("password"));
            } else {
                datamodel.put("success", "-1");
                return;
            }

            if (dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail(tecnico.getEmail()) != null) {
                datamodel.put("success", "-2");
            } else {
                dl.getTecnicoOrdiniDAO().storeTecnicoOrdini(tecnico);
                datamodel.put("success", "2");
            }


            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("tecnici", dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini(0));
                datamodel.put("page", 0);
            }

            result.activate("gestione_tecnici_ordini.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (request.getParameter("render") != null) {
                //Se l'utente richiede qualche elemento non renderizzato

                if ("Modifica".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per la modifica
                    renderModify(request, response, Integer.parseInt(request.getParameter("id")));

                } else if ("Aggiungi".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per l'aggiunta
                    renderInsert(request, response);
                } else renderGestioneTecniciOrdiniPage(request, response);

            } else if (request.getParameter("action") != null) {
                // Se l'utente richiede un'azione

                if ("Modifica".equals(request.getParameter("action"))) {

                    // Se devo effettuare la modifica
                    handleModify(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Elimina".equals(request.getParameter("action"))) {

                    // Se devo effettuare l'eliminazione
                    handleDelete(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Aggiungi".equals(request.getParameter("action"))) {

                    // Se devo effettuare l'aggiunta
                    handleInsert(request, response);
                } else renderGestioneTecniciOrdiniPage(request, response);
            } else {
                renderGestioneTecniciOrdiniPage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

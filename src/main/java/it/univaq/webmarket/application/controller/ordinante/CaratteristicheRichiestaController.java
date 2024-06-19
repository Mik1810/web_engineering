package it.univaq.webmarket.application.controller.ordinante;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
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

public class CaratteristicheRichiestaController extends ApplicationBaseController {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.ORDINANTE);
    }

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {

            if (!parameterMap.containsKey("id_richiesta")) handleError("ID Richiesta not specified", request, response);

            int richiestaID = Integer.parseInt(parameterMap.get("id_richiesta")[0]);
            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaID);
            datamodel.put("caratteristiche", dl.getCaratteristicaDAO().getCaratteristicheConValore(richiesta));
            datamodel.put("richiesta", richiesta);
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("caratteristiche_richiesta.ftl", datamodel, request, response);

    }

    private void renderModify(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();


            if (!parameterMap.containsKey("id")) handleError("ID Richiesta not specified", request, response);
            datamodel.put("visibilityModify", "flex");
            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(Integer.parseInt(parameterMap.get("id_richiesta")[0]));
            datamodel.put("richiesta", richiesta);
            datamodel.put("caratteristiche", dl.getCaratteristicaDAO().getCaratteristicheConValore(richiesta));
            int caratteristicaConValoreID = Integer.parseInt(parameterMap.get("id")[0]);
            CaratteristicaConValore caratteristicaConValore = dl.getCaratteristicaDAO().getCaratteristicaConValore(caratteristicaConValoreID);
            datamodel.put("caratteristicaConValore", caratteristicaConValore);

            result.activate("caratteristiche_richiesta.ftl", datamodel, request, response);
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

            int caratteristicaConValoreID = Integer.parseInt(parameterMap.get("id")[0]);
            int richiestaID = Integer.parseInt(parameterMap.get("id_richiesta")[0]);

            CaratteristicaConValore caratteristicaConValore = dl.getCaratteristicaDAO().getCaratteristicaConValore(caratteristicaConValoreID);
            String valore = parameterMap.containsKey("valore") ? parameterMap.get("valore")[0] : "";

            if (!valore.equals(caratteristicaConValore.getValore())) {
                caratteristicaConValore.setValore(valore);
                datamodel.put("success", "1");
            } else {
                datamodel.put("success", "-1");
            }

            dl.getCaratteristicaDAO().storeCaratteristicaConValore(caratteristicaConValore, richiestaID);

            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaID);
            datamodel.put("caratteristiche", dl.getCaratteristicaDAO().getCaratteristicheConValore(richiesta));
            datamodel.put("richiesta", richiesta);

            result.activate("caratteristiche_richiesta.ftl", datamodel, request, response);
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

            int caratteristicaConValoreId = Integer.parseInt(parameterMap.get("id")[0]);
            int richiestaId = Integer.parseInt(parameterMap.get("id_richiesta")[0]);

            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);
            CaratteristicaConValore caratteristicaConValore = dl.getCaratteristicaDAO().getCaratteristicaConValore(caratteristicaConValoreId);

            dl.getCaratteristicaDAO().deleteCaratteristicaConValore(caratteristicaConValore);

            datamodel.put("richiesta", richiesta);
            datamodel.put("caratteristiche", dl.getCaratteristicaDAO().getCaratteristicheConValore(richiesta));
            datamodel.put("success", "2");

            result.activate("caratteristiche_richiesta.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void renderInsert(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();

            //Se l'utente richiede qualche elemento non renderizzato
            if (parameterMap.containsKey("render")) {
                if ("Modifica".equals(parameterMap.get("render")[0])) {
                    renderModify(request, response);
                } else if("Aggiungi".equals(parameterMap.get("render")[0])) {
                    renderInsert(request, response);
                } else renderTemplate(request, response);

            } else if (parameterMap.containsKey("action")) {
                // Se l'utente richiede un'azione

                if ("Modifica".equals(parameterMap.get("action")[0])) {
                    handleModify(request, response);
                } else if ("Elimina".equals(request.getParameter("action"))) {
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

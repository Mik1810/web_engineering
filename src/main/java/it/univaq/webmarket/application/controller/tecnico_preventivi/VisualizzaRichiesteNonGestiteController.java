package it.univaq.webmarket.application.controller.tecnico_preventivi;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizzaRichiesteNonGestiteController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.TECNICO_PREVENTIVI);
    }

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            /*
            HttpSession session = SecurityHelpers.checkSession(request);
            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail(String.valueOf(session.getAttribute("email")));
            */
            if (parameterMap.containsKey("id")) {
                datamodel.put("richieste", List.of(dl.getRichiestaDAO().getRichiesta(Integer.parseInt(parameterMap.get("id")[0]))));
                datamodel.put("id", parameterMap.get("id")[0]);
            } else if (parameterMap.containsKey("page")) {
                Integer page = Integer.parseInt(parameterMap.get("page")[0]);
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("prendi_in_consegna.ftl", datamodel, request, response);
    }

    private void renderCaratteristiche(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException{
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int richiestaId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);

                datamodel.put("page", page);
                datamodel.put("visibilityModify", "flex");
                datamodel.put("caratteristiche", richiesta.getCaratteristicheConValore());
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(page));
            } else if(parameterMap.containsKey("id")) {

                int ordineId = Integer.parseInt(parameterMap.get("id")[0]);

                Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(ordineId);

                datamodel.put("visibilityModify", "flex");
                datamodel.put("caratteristiche", richiesta.getCaratteristicheConValore());
                datamodel.put("richieste", List.of(richiesta));
                datamodel.put("id", ordineId);
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(0));
            }

            result.activate("prendi_in_consegna.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handlePrendiInConsegna(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int richiestaId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);
                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().createRichiestaPresaInCarico();

                richiestaPresaInCarico.setRichiesta(richiesta);
                richiestaPresaInCarico.setTecnicoPreventivi(tecnicoPreventivi);

                dl.getRichiestaPresaInCaricoDAO().storeRichiestaPresaInCarico(richiestaPresaInCarico);

                datamodel.put("page", page);
                datamodel.put("success", "1");
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(page));

            } else if(parameterMap.containsKey("id")) {

                int richiestaId = Integer.parseInt(parameterMap.get("id")[0]);

                Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(richiestaId);
                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().createRichiestaPresaInCarico();

                richiestaPresaInCarico.setRichiesta(richiesta);
                richiestaPresaInCarico.setTecnicoPreventivi(tecnicoPreventivi);

                dl.getRichiestaPresaInCaricoDAO().storeRichiestaPresaInCarico(richiestaPresaInCarico);

                // Restituisco una lista vuota se effettivamente prendo in consegna questo ordine
                datamodel.put("richieste", List.of());
                datamodel.put("id", richiestaId);
                datamodel.put("success", "1");
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("richieste", dl.getRichiestaDAO().getRichiesteNonGestite(0));
            }

            result.activate("prendi_in_consegna.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();

            if (parameterMap.containsKey("render")) {
                if (parameterMap.get("render")[0].equals("Caratteristiche")) {
                    renderCaratteristiche(request, response);
                } else {
                    renderTemplate(request, response);
                }
            } else if (parameterMap.containsKey("action")) {

                if ("Prendi in consegna".equals(parameterMap.get("action")[0])) {
                    handlePrendiInConsegna(request, response);
                } else {
                    renderTemplate(request, response);
                }
            } else {
                renderTemplate(request, response);
            }
        } catch (TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

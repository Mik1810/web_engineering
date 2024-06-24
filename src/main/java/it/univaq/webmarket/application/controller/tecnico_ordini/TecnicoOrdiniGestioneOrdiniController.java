package it.univaq.webmarket.application.controller.tecnico_ordini;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TecnicoOrdiniGestioneOrdiniController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.TECNICO_ORDINI);
    }

    private void renderGestioneOrdinePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {

            TecnicoOrdini tecnicoOrdini = dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, page));
                datamodel.put("page", page);
            } else {
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, 0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }


        result.activate("tecnico_ordini_gestione_ordini.ftl", datamodel, request, response);
    }


    private void handleModificaOrdineToInConsegna(HttpServletRequest request, HttpServletResponse response, Integer ordine_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            TecnicoOrdini tecnicoOrdini = dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            Ordine ordine = dl.getOrdineDAO().getOrdine(ordine_key);

            ordine.setStatoConsegna(Ordine.StatoConsegna.IN_CONSEGNA);

            dl.getOrdineDAO().storeOrdine(ordine);

            datamodel.put("success", "1"); // Lo stato dell'ordine è: In Consegna.
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, page));
                datamodel.put("page", page);

            } else {
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, 0));
                datamodel.put("page", 0);
            }

            result.activate("tecnico_ordini_gestione_ordini.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleModificaOrdineToConsegnato(HttpServletRequest request, HttpServletResponse response, Integer ordine_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            TecnicoOrdini tecnicoOrdini = dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            Ordine ordine = dl.getOrdineDAO().getOrdine(ordine_key);
            ordine.setStatoConsegna(Ordine.StatoConsegna.CONSEGNATO);
            dl.getOrdineDAO().storeOrdine(ordine);

            datamodel.put("success", "2"); // Lo stato dell'ordine è: Consegnato.
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, page));
                datamodel.put("page", page);

            } else {
                datamodel.put("ordini", dl.getOrdineDAO().getAllOrdiniByTecnicoOrdine(tecnicoOrdini, 0));
                datamodel.put("page", 0);
            }

            result.activate("tecnico_ordini_gestione_ordini.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if ("In consegna".equals(request.getParameter("ordine"))) {
                //Se devo creare un ordine da una proposta accettata
                handleModificaOrdineToInConsegna(request, response, Integer.parseInt(request.getParameter("id")));

            } else if ("Consegnato".equals(request.getParameter("ordine"))) {
                handleModificaOrdineToConsegnato(request, response, Integer.parseInt(request.getParameter("id")));

            } else renderGestioneOrdinePage(request, response);

        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

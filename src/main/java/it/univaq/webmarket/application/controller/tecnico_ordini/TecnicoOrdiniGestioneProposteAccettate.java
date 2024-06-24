package it.univaq.webmarket.application.controller.tecnico_ordini;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
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

public class TecnicoOrdiniGestioneProposteAccettate extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.TECNICO_ORDINI);
    }

    private void renderGestioneProposteAccettatePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        try {
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteAccettate(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteAccettate(0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }


        result.activate("tecnico_ordini_gestione_proposte_accettate.ftl", datamodel, request, response);
    }


    private void handleCreaOrdine(HttpServletRequest request, HttpServletResponse response, Integer proposta_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            TecnicoOrdini tecnicoOrdini = dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            Ordine ordine = dl.getOrdineDAO().createOrdine();
            Proposta proposta = dl.getPropostaDAO().getProposta(proposta_key);

            ordine.setProposta(proposta);
            ordine.setTecnicoOrdini(tecnicoOrdini);

            dl.getOrdineDAO().storeOrdine(ordine);

            datamodel.put("success", "1"); // Ordine creato con successo
            if (request.getParameter("page") != null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("page", page);

            } else datamodel.put("page", 0);

            result.activate("tecnico_ordini_gestione_proposte_accettate", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            System.out.println("TecnicoOrdiniGestioneProposteAccettate");

            if ("Crea Ordine".equals(request.getParameter("ordine"))) {
                //Se devo creare un ordine da una proposta accettata
                handleCreaOrdine(request, response, Integer.parseInt(request.getParameter("id")));

            } else renderGestioneProposteAccettatePage(request, response);

        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

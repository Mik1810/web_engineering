package it.univaq.webmarket.application.controller.ordinante;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Proposta;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizzaProposteController extends ApplicationBaseController {

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

                HttpSession session = SecurityHelpers.checkSession(request);
                Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail(String.valueOf(session.getAttribute("email")));

                if (parameterMap.containsKey("id")) {
                    datamodel.put("proposte", List.of(dl.getPropostaDAO()
                            .getProposta(Integer.parseInt(parameterMap.get("id")[0]))));
                    datamodel.put("id", parameterMap.get("id")[0]);
                } else if(parameterMap.containsKey("page")) {
                    Integer page = Integer.parseInt(parameterMap.get("page")[0]);

                    datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, page));
                    datamodel.put("page", page);
                } else {
                    datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, 0));
                    datamodel.put("page", 0);
                }
            } catch (DataException e) {
                handleError(e, request, response);
            }

            result.activate("proposte.ftl", datamodel, request, response);
        } catch (TemplateManagerException e){
            handleError(e, request, response);
        }
    }

    private void renderDeny(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                datamodel.put("visibilityModify", "flex");
                datamodel.put("propostaDaRifiutare", proposta);
                datamodel.put("page", page);
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, page));
            } else if(parameterMap.containsKey("id")) {

                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                datamodel.put("visibilityModify", "flex");
                datamodel.put("propostaDaRifiutare", proposta);
                datamodel.put("proposte", List.of(proposta));
                datamodel.put("id", propostaId);
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, 0));
            }

            result.activate("proposte.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleDeny(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);
                String motivazione = parameterMap.get("motivazione")[0];

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                proposta.setStatoProposta(Proposta.StatoProposta.RIFIUTATO);
                proposta.setMotivazione(motivazione);

                dl.getPropostaDAO().storeProposta(proposta);

                datamodel.put("page", page);
                datamodel.put("success", "2");
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, page));
            } else if(parameterMap.containsKey("id")) {

                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);
                String motivazione = parameterMap.get("motivazione")[0];

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                proposta.setStatoProposta(Proposta.StatoProposta.RIFIUTATO);
                proposta.setMotivazione(motivazione);

                dl.getPropostaDAO().storeProposta(proposta);

                datamodel.put("proposte", List.of(proposta));
                datamodel.put("id", propostaId);
                datamodel.put("success", "2");
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, 0));
            }

            result.activate("proposte.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleAccept(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                proposta.setStatoProposta(Proposta.StatoProposta.ACCETTATO);

                dl.getPropostaDAO().storeProposta(proposta);

                datamodel.put("page", page);
                datamodel.put("success", "2");
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, page));
            } else if(parameterMap.containsKey("id")) {

                int propostaId = Integer.parseInt(parameterMap.get("id")[0]);

                Proposta proposta = dl.getPropostaDAO().getProposta(propostaId);

                proposta.setStatoProposta(Proposta.StatoProposta.ACCETTATO);

                dl.getPropostaDAO().storeProposta(proposta);

                datamodel.put("proposte", List.of(proposta));
                datamodel.put("id", propostaId);
                datamodel.put("success", "2");
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, 0));
            }

            result.activate("proposte.ftl", datamodel, request, response);
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
                if ("Rifiuta".equals(parameterMap.get("render")[0])) {
                    //Se devo renderizzare il menù per il rifiuto della proposta
                    renderDeny(request, response);
                } else renderTemplate(request, response);
            } else if (parameterMap.containsKey("action")) {
                // Se l'utente richiede un'azione
                if ("Rifiuta".equals(parameterMap.get("action")[0])) {
                    // Se devo effettuare il rifiuto
                    handleDeny(request, response);
                } else if ("Accetta".equals(parameterMap.get("action")[0])) {
                    // Se devo effettuare l'eliminazione
                    handleAccept(request, response);
                } else renderTemplate(request, response);
            } else {
                renderTemplate(request, response);
            }
        } catch (TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

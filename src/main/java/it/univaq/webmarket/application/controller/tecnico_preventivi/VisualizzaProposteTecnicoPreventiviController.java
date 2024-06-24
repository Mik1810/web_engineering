package it.univaq.webmarket.application.controller.tecnico_preventivi;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizzaProposteTecnicoPreventiviController extends ApplicationBaseController {

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

            HttpSession session = SecurityHelpers.checkSession(request);
            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail(String.valueOf(session.getAttribute("email")));

            if (parameterMap.containsKey("id_richiesta_presa_in_carico")) {
                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(Integer.parseInt(parameterMap.get("id_richiesta_presa_in_carico")[0]));
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteByRichiestaPresaInCarico(richiestaPresaInCarico));
            } else if (parameterMap.containsKey("page")) {
                Integer page = Integer.parseInt(parameterMap.get("page")[0]);
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteByTecnicoPreventivi(tecnicoPreventivi, page));
                datamodel.put("page", page);
            } else {
                datamodel.put("proposte", dl.getPropostaDAO().getAllProposteByTecnicoPreventivi(tecnicoPreventivi, 0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("proposte_tecnico_preventivi.ftl", datamodel, request, response);
    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            renderTemplate(request, response);
        } catch (TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

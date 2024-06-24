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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TecnicoPreventiviController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.TECNICO_PREVENTIVI);
    }

    private void rendereTecnicoPreventiviPage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            HttpSession session = SecurityHelpers.checkSession(request);

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail(session.getAttribute("email").toString());
            List<Richiesta> richieste = dl.getRichiestaDAO().getRichiesteNonGestite(0);
            List<RichiestaPresaInCarico> richiestePreseInCarico = dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, 0);
            List<Proposta> proposte = dl.getPropostaDAO().getAllProposteByTecnicoPreventivi(tecnicoPreventivi, 0);

            richieste = richieste.size() > 3 ? richieste.subList(0, 3) : richieste;
            richiestePreseInCarico = richiestePreseInCarico.size() > 2 ? richiestePreseInCarico.subList(0, 2) : richiestePreseInCarico;
            proposte = proposte.size() > 3 ? proposte.subList(0, 3) : proposte;

            datamodel.put("richieste", richieste);
            datamodel.put("richiestePreseInCarico", richiestePreseInCarico);

            result.activate("tecnico_preventivi.ftl", datamodel, request, response);
        } catch (DataException e) {
            handleError(e, request, response);
        }

    }


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            rendereTecnicoPreventiviPage(request, response);
        } catch (TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

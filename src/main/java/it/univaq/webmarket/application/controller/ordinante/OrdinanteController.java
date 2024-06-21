package it.univaq.webmarket.application.controller.ordinante;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.Ordine;
import it.univaq.webmarket.data.model.Proposta;
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
import java.util.stream.Collectors;

public class OrdinanteController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.ORDINANTE);
    }

    protected void renderOrdinantePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        HttpSession session = SecurityHelpers.checkSession(request);


        try {
            Ordinante ordinante = dl.getOrdinanteDAO().getOrdinanteByEmail(session.getAttribute("email").toString());
            List<Richiesta> richieste = dl.getRichiestaDAO().getRichiesteByOrdinante(ordinante, 0);
            if(richieste.size() > 3) {
                richieste = richieste.subList(0, 3);
            }

            List<Proposta> proposte = dl.getPropostaDAO().getAllProposteDaDecidereByOrdinante(ordinante, 0);
            if(proposte.size() > 3) {
                proposte = proposte.subList(0, 3);
            }

            List<Ordine> ordini = dl.getOrdineDAO().getAllOrdiniByOrdinante(ordinante, 0)
                    .stream()
                    .filter(ordine -> !ordine.getStatoConsegna().equals("Consegnato"))
                    .collect(Collectors.toList());
            if(ordini.size() > 3) {
                ordini = ordini.subList(0, 3);
            }

            datamodel.put("richieste", richieste);
            datamodel.put("proposte", proposte);
            datamodel.put("ordini", ordini);
        } catch (DataException e) {
            handleError(e, request, response);
        }
        result.activate("ordinante.ftl", datamodel, request, response);
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            renderOrdinantePage(request, response);
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

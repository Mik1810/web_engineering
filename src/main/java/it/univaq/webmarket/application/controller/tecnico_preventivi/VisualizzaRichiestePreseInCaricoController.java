package it.univaq.webmarket.application.controller.tecnico_preventivi;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Proposta;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizzaRichiestePreseInCaricoController extends ApplicationBaseController {

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

            if (parameterMap.containsKey("id")) {
                datamodel.put("richiestePreseInCarico", List.of(dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(Integer.parseInt(parameterMap.get("id")[0]))));
                datamodel.put("id", parameterMap.get("id")[0]);
            } else if (parameterMap.containsKey("page")) {
                Integer page = Integer.parseInt(parameterMap.get("page")[0]);
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
                datamodel.put("page", page);
            } else {
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, 0));
                datamodel.put("page", 0);
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("richieste_prese_in_carico.ftl", datamodel, request, response);
    }

    private void renderCaratteristiche(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException{
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                datamodel.put("page", page);
                datamodel.put("visibilityCaratteristiche", "flex");
                datamodel.put("caratteristiche", richiestaPresaInCarico.getRichiesta().getCaratteristicheConValore());
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
            } else if(parameterMap.containsKey("id")) {

                int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);

                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                datamodel.put("visibilityCaratteristiche", "flex");
                datamodel.put("caratteristiche", richiestaPresaInCarico.getRichiesta().getCaratteristicheConValore());
                datamodel.put("richiestePreseInCarico", List.of(richiestaPresaInCarico));
                datamodel.put("id", richiestaPresaInCaricoId);
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, 0));
            }

            result.activate("richieste_prese_in_carico.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void renderProposta(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {
                int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);
                int page = Integer.parseInt(parameterMap.get("page")[0]);

                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                datamodel.put("page", page);
                datamodel.put("visibilityProposta", "flex");
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
                datamodel.put("richiestaPresaInCarico", richiestaPresaInCarico);
            } else if(parameterMap.containsKey("id")) {

                int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);

                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                datamodel.put("visibilityProposta", "flex");
                datamodel.put("richiestePreseInCarico", List.of(richiestaPresaInCarico));
                datamodel.put("richiestaPresaInCarico", richiestaPresaInCarico);
                datamodel.put("id", richiestaPresaInCaricoId);
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, 0));
            }

            result.activate("richieste_prese_in_carico.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleProposta(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();

            TecnicoPreventivi tecnicoPreventivi = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail((String) SecurityHelpers.checkSession(request).getAttribute("email"));

            if(parameterMap.containsKey("page")) {

                    int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);
                    int page = Integer.parseInt(parameterMap.get("page")[0]);

                try {
                    RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                    String produttore = parameterMap.get("produttore")[0];
                    Float prezzo = Float.parseFloat(parameterMap.get("prezzo")[0]);
                    String nomeProdotto = parameterMap.get("nome_prodotto")[0];
                    String URL = new URL(parameterMap.get("URL")[0]).toString(); // Check if URL is valid (throws MalformedURLException if not valid)
                    String note = parameterMap.get("note")[0];

                    Proposta proposta = dl.getPropostaDAO().createProposta();
                    proposta.setProduttore(produttore);
                    proposta.setPrezzo(prezzo);
                    proposta.setNomeProdotto(nomeProdotto);
                    proposta.setURL(URL);
                    proposta.setNote(note);
                    proposta.setStatoProposta(Proposta.StatoProposta.IN_ATTESA);
                    proposta.setRichiestaPresaInCarico(richiestaPresaInCarico);

                    dl.getPropostaDAO().storeProposta(proposta);

                    datamodel.put("page", page);
                    datamodel.put("success", "1");
                    datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
                } catch(NumberFormatException e) {
                    datamodel.put("page", page);
                    datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
                    datamodel.put("success", "-1");
                } catch (MalformedURLException e) {
                    datamodel.put("page", page);
                    datamodel.put("richiestePreseInCarico", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, page));
                    datamodel.put("success", "-2");
                }
            } else if(parameterMap.containsKey("id")) {

                int richiestaPresaInCaricoId = Integer.parseInt(parameterMap.get("id")[0]);

                RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(richiestaPresaInCaricoId);

                try {

                    String produttore = parameterMap.get("produttore")[0];
                    Float prezzo = Float.parseFloat(parameterMap.get("prezzo")[0]);
                    String nomeProdotto = parameterMap.get("nome_prodotto")[0];
                    String URL = new URL(parameterMap.get("URL")[0]).toString(); // Check if URL is valid (throws MalformedURLException if not valid)
                    String note = parameterMap.get("note")[0];

                    Proposta proposta = dl.getPropostaDAO().createProposta();
                    proposta.setProduttore(produttore);
                    proposta.setPrezzo(prezzo);
                    proposta.setNomeProdotto(nomeProdotto);
                    proposta.setURL(URL);
                    proposta.setNote(note);
                    proposta.setStatoProposta(Proposta.StatoProposta.IN_ATTESA);
                    proposta.setRichiestaPresaInCarico(richiestaPresaInCarico);

                    dl.getPropostaDAO().storeProposta(proposta);

                    datamodel.put("richiestePreseInCarico", List.of(richiestaPresaInCarico));
                    datamodel.put("id", richiestaPresaInCaricoId);
                    datamodel.put("success", "1");
                } catch(NumberFormatException e) {
                    datamodel.put("richiestePreseInCarico", List.of(richiestaPresaInCarico));
                    datamodel.put("id", richiestaPresaInCaricoId);
                    datamodel.put("success", "-1");
                } catch(MalformedURLException e) {
                    datamodel.put("richiestePreseInCarico", List.of(richiestaPresaInCarico));
                    datamodel.put("id", richiestaPresaInCaricoId);
                    datamodel.put("success", "-2");
                }
            } else {
                //Questo perchè potrebbe cancellare dalla URL il parametro id o page
                datamodel.put("page", 0);
                datamodel.put("richieste", dl.getRichiestaPresaInCaricoDAO().getAllRichiestePresaInCaricoByTecnicoPreventivi(tecnicoPreventivi, 0));
            }

            result.activate("richieste_prese_in_carico.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();

            if (parameterMap.containsKey("render")) {
                switch (parameterMap.get("render")[0]) {
                    case "Caratteristiche":
                        renderCaratteristiche(request, response);
                        break;
                    case "Crea Proposta":
                        renderProposta(request, response);
                        break;
                    default:
                        renderTemplate(request, response);
                        break;
                }
            } else if (parameterMap.containsKey("action")) {

                if ("Crea".equals(parameterMap.get("action")[0])) {
                    handleProposta(request, response);
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

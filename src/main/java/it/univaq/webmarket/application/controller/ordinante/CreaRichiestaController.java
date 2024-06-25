package it.univaq.webmarket.application.controller.ordinante;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaRichiestaController extends ApplicationBaseController {

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre());

            result.activate("crea_richiesta.ftl", datamodel, request, response);
        } catch (DataException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleRequests(HttpServletRequest request, HttpServletResponse response) {

        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            if (parameterMap.containsKey("categoriaPadre")) {
                int idCategoriaPadre = Integer.parseInt(parameterMap.get("categoriaPadre")[0]);
                ObjectMapper mapper = new ObjectMapper();
                CategoriaPadre categoriaPadre = dl.getCategoriaDAO()
                        .getCategoriaPadre(idCategoriaPadre);
                String json = mapper.writeValueAsString(dl.getCategoriaDAO().getCategorieFiglioByPadre(categoriaPadre));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().print(json);
                response.getWriter().flush();

            } else if (parameterMap.containsKey("categoriaFiglio")) {

                int idCategoriaFiglio = Integer.parseInt(parameterMap.get("categoriaFiglio")[0]);
                ObjectMapper mapper = new ObjectMapper();
                CategoriaFiglio categoriafiglio = dl.getCategoriaDAO()
                        .getCategoriaFiglio(idCategoriaFiglio);
                String json = mapper.writeValueAsString(dl.getCategoriaDAO().getCategorieNipoteByFiglio(categoriafiglio));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().print(json);
                response.getWriter().flush();

            } else if (parameterMap.containsKey("categoriaNipote")) {

                int idCategoriaNipote = Integer.parseInt(parameterMap.get("categoriaNipote")[0]);
                ObjectMapper mapper = new ObjectMapper();
                CategoriaNipote categoriaNipote = dl.getCategoriaDAO()
                        .getCategoriaNipote(idCategoriaNipote);
                List<Caratteristica> caratteristiche = dl.getCaratteristicaDAO().getAllCaratteristiche(categoriaNipote);
                String json = mapper.writeValueAsString(caratteristiche);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().print(json);
                response.getWriter().flush();

            } else if(parameterMap.containsKey("key")){
                Richiesta richiesta = dl.getRichiestaDAO().createRichiesta();
                richiesta.setData(LocalDate.now());

                // POtrebbe essere null ma viene gestito dal DAO
                richiesta.setNote(parameterMap.get("note")[0]);

                HttpSession session = SecurityHelpers.checkSession(request);
                Ordinante ordinante = dl.getOrdinanteDAO()
                        .getOrdinanteByEmail((String) session.getAttribute("email"));
                richiesta.setOrdinante(ordinante);

                dl.getRichiestaDAO().storeRichiesta(richiesta);
                int richiestaID = richiesta.getKey();

                String[] keys = parameterMap.get("key");

                for(String key : keys) {
                    if(!"".equals(parameterMap.get(key)[0])){
                        CaratteristicaConValore caratteristicaConValore = dl.getCaratteristicaDAO().createCaratteristicaConValore();
                        Caratteristica caratteristica = dl.getCaratteristicaDAO()
                                .getCaratteristica(Integer.parseInt(key));

                        caratteristicaConValore.setCaratteristica(caratteristica);
                        caratteristicaConValore.setValore(parameterMap.get(key)[0]);
                        dl.getCaratteristicaDAO().storeCaratteristicaConValore(caratteristicaConValore, richiestaID);
                    }
                }
                EmailSender sender = (EmailSender) getServletContext().getAttribute("emailsender");
                sender.sendPDFWithEmail(getServletContext(), ordinante.getEmail(), richiesta, EmailSender.Event.RICHIESTA_REGISTRATA);

                response.sendRedirect("ordinante");
            } else {
                renderTemplate(request, response);
            }
        } catch(DataException | IOException ex) {
            handleError(ex, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap.isEmpty()) {
            renderTemplate(request, response);
        } else {
            handleRequests(request, response);
        }
    }

}

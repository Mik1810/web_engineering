package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.utils.Ruolo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaFiglioController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.AMMINISTRATORE);
    }

    private void renderCategoriePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {   //all'avvio della pagina
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        try {
            if (request.getParameter("id_categoria_genitore") != null) {
                CategoriaPadre categoriaPadre = dl.getCategoriaDAO()
                        .getCategoriaPadre(Integer.parseInt(request.getParameter("id_categoria_genitore")));
                List<CategoriaFiglio> categorieFiglio = dl.getCategoriaDAO().getCategorieFiglioByPadre(categoriaPadre);
                datamodel.put("categorie", categorieFiglio);

                datamodel.put("id_categoria_genitore", request.getParameter("id_categoria_genitore"));
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        if (request.getParameter("showAlert") != null) {
            if (request.getParameter("showAlert").equals("1")) {
                datamodel.put("success", "1");
            }
            if (request.getParameter("showAlert").equals("2")) {
                datamodel.put("success", "2");
            }
        }

        result.activate("categorie_figlio.ftl", datamodel, request, response);
    }

    private void renderModify(HttpServletRequest request, HttpServletResponse response, Integer categoriaFiglio_key) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key);
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            if (!request.getParameter("id_categoria_genitore").equals("null")) {


                CategoriaPadre categoriaPadre = dl.getCategoriaDAO()
                        .getCategoriaPadre(Integer.parseInt(request.getParameter("id_categoria_genitore")));
                List<CategoriaFiglio> categorieFiglio = dl.getCategoriaDAO().getCategorieFiglioByPadre(categoriaPadre);
                datamodel.put("id_categoria_genitore", request.getParameter("id_categoria_genitore"));
                datamodel.put("categorie", categorieFiglio);
                datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre(0));
                datamodel.put("categoriaGenitoreEsistente", categoriaFiglio.getCategoriaGenitore());
                datamodel.put("visibilityUpdate", "flex");
                datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key));
            } else {


                try {
                    datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key));
                    datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                    datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre(0));
                    datamodel.put("categoriaGenitoreEsistente", categoriaFiglio.getCategoriaGenitore());
                    datamodel.put("visibilityUpdate", "flex");
                } catch (DataException e) {
                    handleError(e, request, response);
                }
            }

            result.activate("categorie_figlio.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }

    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response, Integer categoriaFiglio_key) {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key);
            dl.getCategoriaDAO().deleteCategoriaFiglio(categoriaFiglio);


            String id_categoria_genitore = request.getParameter("id_categoria_genitore");


            if (id_categoria_genitore.equals("null")) {
                response.sendRedirect("categoria_figlio");
            } else {
                response.sendRedirect("categoria_figlio?id_categoria_genitore=" + id_categoria_genitore);
            }
            response.sendRedirect("categoria_figlio");
        } catch (IOException | DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleModify(HttpServletRequest request, HttpServletResponse response, Integer categoriaFiglio_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key);
            categoriaFiglio.setNome(request.getParameter("nome"));

            String id_categoria_genitore = request.getParameter("id_categoria_genitore");

            if (request.getParameter("sceltaCategoriaPadre") != null) {
                categoriaFiglio.getCategoriaGenitore().setKey(Integer.parseInt(request.getParameter("sceltaCategoriaPadre")));
            }

            dl.getCategoriaDAO().storeCategoriaFiglio(categoriaFiglio);

            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            try {
                datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key));
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                datamodel.put("success", "1");

            } catch (DataException e) {
                handleError(e, request, response);
            }


            if (id_categoria_genitore.equals("null")) {
                result.activate("categorie_figlio.ftl", datamodel, request, response);
            } else {
                response.sendRedirect("categoria_figlio?id_categoria_genitore=" + id_categoria_genitore + "&showAlert=1");
            }


        } catch (DataException ex) {
            handleError(ex, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().createCategoriaFiglio();

            if (request.getParameter("nome") != null && !request.getParameter("nome").equals("")) {
                categoriaFiglio.setNome(request.getParameter("nome"));
            } else {
                datamodel.put("success", "-2");
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                result.activate("categorie_figlio.ftl", datamodel, request, response);
                return;
            }

            if (request.getParameter("sceltaCategoriaPadre") != null && !request.getParameter("sceltaCategoriaPadre").isEmpty()) {
                categoriaFiglio.setCategoriaGenitore(dl.getCategoriaDAO().getCategoriaPadre(Integer.parseInt(request.getParameter("sceltaCategoriaPadre"))));
            } else {
                datamodel.put("success", "-1");
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                result.activate("categorie_figlio.ftl", datamodel, request, response);
                return;
            }


            dl.getCategoriaDAO().storeCategoriaFiglio(categoriaFiglio);

            try {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                datamodel.put("success", "2");

            } catch (DataException e) {
                handleError(e, request, response);
            }
            result.activate("categorie_figlio.ftl", datamodel, request, response);

        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void renderInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
            datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre(0));
            datamodel.put("visibilityInsert", "flex");

            result.activate("categorie_figlio.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleCancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("id_categoria_genitore") != null) {
            response.sendRedirect("categoria_figlio?id_categoria_genitore=" + request.getParameter("id_categoria_genitore"));
        } else {
            response.sendRedirect("categoria_figlio");
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {

            if (request.getParameter("render") != null) {
                //Se l'utente richiede qualche elemento non renderizzato

                if ("Modifica".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per la modifica
                    renderModify(request, response, Integer.parseInt(request.getParameter("id")));

                } else if ("Aggiungi".equals(request.getParameter("render"))) {
                    //Se devo renderizzare il menù per l'aggiunta
                    renderInsert(request, response);
                } else renderCategoriePage(request, response);

            } else if (request.getParameter("action") != null) {
                // Se l'utente richiede un'azione

                if ("Modifica".equals(request.getParameter("action"))) {
                    // Se devo effettuare la modifica
                    handleModify(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Elimina".equals(request.getParameter("action"))) {
                    // Se devo effettuare l'eliminazione
                    handleDelete(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Aggiungi".equals(request.getParameter("action"))) {

                    // Se devo effettuare l'aggiunta
                    handleInsert(request, response);
                } else if ("Annulla".equals(request.getParameter("action"))) {
                    // Se voglio annullare l'azione
                    handleCancel(request, response);
                } else renderCategoriePage(request, response);
            } else {
                renderCategoriePage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

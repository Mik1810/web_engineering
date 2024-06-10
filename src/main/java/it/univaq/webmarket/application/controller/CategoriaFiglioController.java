package it.univaq.webmarket.application.controller;

import freemarker.template.TemplateException;
import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.impl.CategoriaDAO_MySQL;
import it.univaq.webmarket.data.model.Categoria;
import it.univaq.webmarket.data.model.CategoriaFiglio;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.utils.Ruolo;
import it.univaq.webmarket.framework.utils.ServletHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaFiglioController extends ApplicationBaseController {

    private void renderCategoriePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {   //all'avvio della pagina
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            if (request.getParameter("id") != null) {
                CategoriaPadre categoriaPadre = dl.getCategoriaDAO()
                        .getCategoriaPadre(Integer.parseInt(request.getParameter("id")));
                System.out.println(categoriaPadre.getNome());
                List<CategoriaFiglio> categorieFiglio = dl.getCategoriaDAO().getCategorieFiglioByPadre(categoriaPadre);
                categorieFiglio.forEach(c -> System.out.println(c.getNome()));
                datamodel.put("categorie", categorieFiglio);
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("categorie_figlio.ftl", datamodel, request, response);
    }

    private void renderModify(HttpServletRequest request, HttpServletResponse response, Integer categoriaFiglio_key) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key);
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            try {
                datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaFiglio(categoriaFiglio_key));
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre());
                datamodel.put("categoriaGenitoreEsistente", categoriaFiglio.getCategoriaGenitore());
                datamodel.put("visibilityUpdate", "flex");


            } catch (DataException e) {
                handleError(e, request, response);
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

            result.activate("categorie_figlio.ftl", datamodel, request, response);

        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            CategoriaFiglio categoriaFiglio = dl.getCategoriaDAO().createCategoriaFiglio();

            if (request.getParameter("sceltaCategoriaPadre") != null || request.getParameter("sceltaCategoriaPadre") != "") {
                categoriaFiglio.setCategoriaGenitore(dl.getCategoriaDAO().getCategoriaPadre(Integer.parseInt(request.getParameter("sceltaCategoriaPadre"))));
            } else {
                datamodel.put("success", "-1");
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategorieFiglio());
                result.activate("categorie_figlio.ftl", datamodel, request, response);
                return;
            }

            if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
                categoriaFiglio.setNome(request.getParameter("nome"));
            } else {
                datamodel.put("success", "-2");
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
            datamodel.put("categoriePadre", dl.getCategoriaDAO().getAllCategoriePadre());
            datamodel.put("visibilityInsert", "flex");

            result.activate("categorie_figlio.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
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
                    response.sendRedirect("categoria_figlio");
                } else renderCategoriePage(request, response);
            } else {
                renderCategoriePage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

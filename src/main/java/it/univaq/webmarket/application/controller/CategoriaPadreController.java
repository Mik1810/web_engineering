package it.univaq.webmarket.application.controller;

import freemarker.template.TemplateException;
import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.impl.CategoriaDAO_MySQL;
import it.univaq.webmarket.data.model.Categoria;
import it.univaq.webmarket.data.model.CategoriaPadre;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.utils.Ruolo;
import it.univaq.webmarket.framework.utils.ServletHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaPadreController extends ApplicationBaseController {

    private void renderCategoriePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            if (request.getParameter("id") != null) {
                datamodel.put("categorie", List.of(dl.getCategoriaDAO().getCategoriaPadre(Integer.parseInt(request.getParameter("id")))));
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
            }
        } catch (DataException e) {
            handleError(e, request, response);
        }

        result.activate("categorie_padre.ftl", datamodel, request, response);
    }

    private void renderModify(HttpServletRequest request, HttpServletResponse response, Integer categoriaPadre_key) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaPadre categoriaPadre = dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key);
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key));
            datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
            datamodel.put("visibilityUpdate", "flex");

            result.activate("categorie_padre.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }

    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response, Integer categoriaPadre_key) {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaPadre categoriaPadre = dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key);
            dl.getCategoriaDAO().deleteCategoriaPadre(categoriaPadre);
            response.sendRedirect("categoria_padre");
        } catch (IOException | DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleModify(HttpServletRequest request, HttpServletResponse response, Integer categoriaPadre_key) throws TemplateManagerException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            CategoriaPadre categoriaPadre = dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key);
            categoriaPadre.setNome(request.getParameter("nome"));
            dl.getCategoriaDAO().storeCategoriaPadre(categoriaPadre);

            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();
            try {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
                datamodel.put("success", "1");

            } catch (DataException e) {
                handleError(e, request, response);
            }

            result.activate("categorie_padre.ftl", datamodel, request, response);

        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void renderInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
            datamodel.put("visibilityInsert", "flex");

            result.activate("categorie_padre.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        try {
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            TemplateResult result = new TemplateResult(getServletContext());
            Map<String, Object> datamodel = new HashMap<>();

            CategoriaPadre categoriaPadre = dl.getCategoriaDAO().createCategoriaPadre();
            if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
                categoriaPadre.setNome(request.getParameter("nome"));
            }
            dl.getCategoriaDAO().storeCategoriaPadre(categoriaPadre);

            datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
            datamodel.put("success", "2");

            result.activate("categorie_padre.ftl", datamodel, request, response);
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
                    response.sendRedirect("categoria_padre");
                } else renderCategoriePage(request, response);
            } else {
                renderCategoriePage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

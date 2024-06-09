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
import java.util.Map;

public class CategoriaPadreController extends ApplicationBaseController {

    private void renderCategoriePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
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
            try {
                datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key));
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre());
                datamodel.put("visibility", "block");
            } catch (DataException e) {
                handleError(e, request, response);
            }

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
            response.sendRedirect("categorie_padre");
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
                datamodel.put("success", "true");

            } catch (DataException e) {
                handleError(e, request, response);
            }

            result.activate("categorie_padre.ftl", datamodel, request, response);

        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (request.getParameter("action") != null || request.getParameter("actionModify") != null) {
                if (request.getParameter("id") == null) handleError("ID not allowed", request, response);
                ServletHelpers.printRequest(request);
                if ("Modifica".equals(request.getParameter("action"))) {
                    renderModify(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Modifica".equals(request.getParameter("actionModify"))) {
                    handleModify(request, response, Integer.parseInt(request.getParameter("id")));
                } else if ("Elimina".equals(request.getParameter("action"))) {
                    System.out.println("Ciao");
                    handleDelete(request, response, Integer.parseInt(request.getParameter("id")));
                } else handleError("Action not allowed", request, response);
            } else {
                renderCategoriePage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }
}

package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
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

public class CategoriaPadreController extends ApplicationBaseController {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ruoliAutorizzati = List.of(Ruolo.AMMINISTRATORE);
    }

    private void renderCategoriePage(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        try {
            if (request.getParameter("id") != null) {
                datamodel.put("categorie", List.of(dl.getCategoriaDAO().getCategoriaPadre(Integer.parseInt(request.getParameter("id")))));
            } else {
                if(request.getParameter("page")!= null) {
                    Integer page = Integer.parseInt(request.getParameter("page"));
                    datamodel.put("categorie", dl.getCategoriaDAO()
                            .getAllCategoriePadre(page));
                    datamodel.put("page", page);
                } else {
                    datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre(0));
                    datamodel.put("page", 0);
                }
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

            System.out.println("Page:"+request.getParameter("page"));
            datamodel.put("categoriaModifica", dl.getCategoriaDAO().getCategoriaPadre(categoriaPadre_key));
            if(request.getParameter("page")!= null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("categorie", dl.getCategoriaDAO()
                        .getAllCategoriePadre(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre(0));
                datamodel.put("page", 0);
            }
            // TODO: da chiedere a giacomo ahahhahah
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
            if(request.getParameter("page")!= null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                response.sendRedirect("categoria_padre?page="+page);
            } else response.sendRedirect("categoria_padre?page=0");
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
                if(request.getParameter("page")!= null) {
                    Integer page = Integer.parseInt(request.getParameter("page"));
                    datamodel.put("categorie", dl.getCategoriaDAO()
                            .getAllCategoriePadre(page));
                    datamodel.put("page", page);
                } else {
                    datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre(0));
                    datamodel.put("page", 0);
                }
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

            if(request.getParameter("page")!= null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("categorie", dl.getCategoriaDAO()
                        .getAllCategoriePadre(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre(0));
                datamodel.put("page", 0);
            }

            //TODO: da chiedere a giacomo ahahhahah
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

            if(request.getParameter("page")!= null) {
                Integer page = Integer.parseInt(request.getParameter("page"));
                datamodel.put("categorie", dl.getCategoriaDAO()
                        .getAllCategoriePadre(page));
                datamodel.put("page", page);
            } else {
                datamodel.put("categorie", dl.getCategoriaDAO().getAllCategoriePadre(0));
                datamodel.put("page", 0);
            }
            datamodel.put("success", "2");

            result.activate("categorie_padre.ftl", datamodel, request, response);
        } catch (DataException ex) {
            handleError(ex, request, response);
        }
    }

    /*
    private List<CategoriaPadre> loadCategoriePadre(HttpServletRequest request, HttpServletResponse response, Integer page) throws DataException {
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        return dl.getCategoriaDAO().getAllCategoriePadre(page);
    }*/

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {

            /*
            ServletHelpers.printRequest(request);
            if(request.getParameter("page") != null) {
                try {
                    Integer page = Integer.parseInt(request.getParameter("page"));
                    System.out.println("Page:"+page);
                    ObjectMapper mapper = new ObjectMapper();

                    String json = mapper.writeValueAsString(loadCategoriePadre(request, response, page));

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    response.getWriter().print(json);
                    response.getWriter().flush();
                    return;
                } catch(DataException e){
                    e.printStackTrace();
                }
            }*/

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

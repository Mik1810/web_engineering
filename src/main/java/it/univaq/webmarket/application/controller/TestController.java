package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.framework.result.HTMLResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestController extends ApplicationBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Test");
        List.of(dl.getAmministratoreDAO().getAmministratoreByEmail("admin@gmail.com")).forEach(e -> result.appendToBody(e.toString() + "<br>"));
        dl.getOrdinanteDAO().getAllOrdinanti().forEach(e -> result.appendToBody(e.toString() + "<br>"));
        dl.getTecnicoPreventiviDAO().getAllTecnicoPreventivi().forEach(e -> result.appendToBody(e.toString()+ "<br>"));
        dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini().forEach(e -> result.appendToBody(e.toString()+ "<br>"));
        result.activate(request, response);
    }
}

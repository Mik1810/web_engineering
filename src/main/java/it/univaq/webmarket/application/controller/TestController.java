package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.data.model.TecnicoOrdini;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.proxy.OrdinanteProxy;
import it.univaq.webmarket.data.model.impl.proxy.TODOTecnicoPreventiviProxy;
import it.univaq.webmarket.data.model.impl.proxy.TODOUfficioProxy;
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

        //Saving an ordinante
        TecnicoPreventivi tp = new TODOTecnicoPreventiviProxy(dl);
        tp.setEmail("tptest1@gmail.com");
        tp.setPassword("tppassword");
        dl.getTecnicoPreventiviDAO().storeTecnicoPreventivi(tp);
        List.of(dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail("tptest1@gmail.com")).forEach(e -> result.appendToBody(e.toString() + "<br>"));
        tp = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail("tptest1@gmail.com");
        System.out.println(tp.getKey());
        dl.getTecnicoPreventiviDAO().deleteTecnicoPreventivi(tp);
        result.activate(request, response);
    }
}

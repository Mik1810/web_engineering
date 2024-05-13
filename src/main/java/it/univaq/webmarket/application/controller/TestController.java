package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestController extends ApplicationBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        dl.getOrdinanteDAO().getAllOrdinanti().forEach(System.out::println);

        dl.getTecnicoPreventiviDAO().getAllTecnicoPreventivi().forEach(System.out::println);
    }
}

package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TestController extends ApplicationBaseController {


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            PrintWriter out = response.getWriter();
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            Ordinante ordinante = dl.getOrdinanteDAO().getOrdinante(1);
            List<Proposta> proposte = dl.getPropostaDAO().getAllProposteByOrdinante(ordinante, 0);
            System.out.println(proposte);

            out.println(proposte);
            out.println();

        } catch (IOException | DataException e) {
            e.printStackTrace();
        }
    }

}

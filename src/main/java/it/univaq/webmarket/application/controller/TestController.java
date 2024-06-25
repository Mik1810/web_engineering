package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.framework.utils.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TestController extends ApplicationBaseController {


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            PrintWriter out = response.getWriter();
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

            EmailSender sender = (EmailSender) getServletContext().getAttribute("emailsender");
            RichiestaPresaInCarico richiestaPresaInCarico = dl.getRichiestaPresaInCaricoDAO().getRichiestaPresaInCarico(1);
            sender.sendPDFWithEmail(getServletContext(), "michaelpiccirilli3@gmail.com", richiestaPresaInCarico, EmailSender.Event.RICHIESTA_PRESA_IN_CARICO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

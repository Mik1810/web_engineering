package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.framework.data.DataException;

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

            List<Ordine> storico = dl.getOrdineDAO().getStorico(ordinante, 0);
            List<Ordine> ordini = dl.getOrdineDAO().getAllOrdiniByOrdinante(ordinante, 0);

            System.out.println("Storico: "+storico);
            out.println("Storico ordini:");
            out.println(storico);
            out.println();

            System.out.println("Ordini: "+ordini);
            out.println("Ordini:");
            out.println(ordini);
            out.println();

            System.out.println("Ordine 1 in storico: "+dl.getOrdineDAO().getOrdineInStorico(1));
            out.println("Ordine 1 in storico:");
            out.println(dl.getOrdineDAO().getOrdineInStorico(1));
            out.println();

        } catch (IOException | DataException e) {
            e.printStackTrace();
        }
    }

}

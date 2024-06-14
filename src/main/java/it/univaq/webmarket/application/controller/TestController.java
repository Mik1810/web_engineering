package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.CaratteristicaDAO;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.utils.ServletHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestController extends ApplicationBaseController {


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            PrintWriter out = response.getWriter();
            WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
            Richiesta richiesta = dl.getRichiestaDAO().getRichiesta(1);
            System.out.println(richiesta);
            System.out.println(richiesta.getCaratteristicheConValore().toString());

            out.println(richiesta);
            out.println();

            out.println(richiesta.getCaratteristicheConValore());
            out.println();

            CaratteristicaConValore caratteristicaConValore = dl.getCaratteristicaDAO().createCaratteristicaConValore();
            caratteristicaConValore.setCaratteristica(dl.getCaratteristicaDAO().getCaratteristica(5));
            caratteristicaConValore.setValore("120");
            dl.getCaratteristicaDAO()
                    .storeCaratteristicaConValore(caratteristicaConValore, richiesta.getKey());
            richiesta.getCaratteristicheConValore().add(caratteristicaConValore);
            response.getWriter().println(richiesta.getCaratteristicheConValore());
            response.getWriter().println();


        } catch (IOException | DataException e) {
            e.printStackTrace();
        }
    }

}

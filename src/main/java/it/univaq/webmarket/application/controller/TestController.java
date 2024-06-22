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

            String password = SecurityHelpers.getPasswordHashPBKDF2("giacomopass");
            System.out.println(password);
            out.println("Giacomo: "+password);
            out.println();

            password = SecurityHelpers.getPasswordHashPBKDF2("techprevpass1");
            System.out.println(password);
            out.println("techprevpass1: "+password);
            out.println();

            password = SecurityHelpers.getPasswordHashPBKDF2("techprevpass2");
            System.out.println(password);
            out.println("techprevpass2: "+password);
            out.println();

            password = SecurityHelpers.getPasswordHashPBKDF2("techordpass2");
            System.out.println(password);
            out.println("techordpass2: "+password);
            out.println();

            password = SecurityHelpers.getPasswordHashPBKDF2("techordpass2");
            System.out.println(password);
            out.println("techordpass2: "+password);
            out.println();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

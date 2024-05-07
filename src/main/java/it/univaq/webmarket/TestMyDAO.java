/*
 * TestMyDAO.java
 *
 * Questo esempio mostra come creare totalmente "a mano" un data model e i suoi DAO
 * 
 * This example shows how to use manually-craft a data model and its DAOs
 *
 */
package it.univaq.webmarket;

import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
import it.univaq.webmarket.data.DAO.impl.WebmarketDataLayer;
import it.univaq.webmarket.data.model.RichiestaAcquisto;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.data.DataLayer;
import it.univaq.webmarket.framework.result.HTMLResult;
import it.univaq.webmarket.framework.utils.ServletHelpers;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class TestMyDAO extends HttpServlet {

    private DataSource ds;

    private void action_manipulate(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {

        //preleviamo il data layer 
        //get the data layer
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        //manipoliamo i dati usando le interfacce esposta dai DAO accessibili dal data layer
        //manipulate the data using the interfaces exposed by the DAOs accessible from the data layer

        RichiestaAcquistoDAO richiestaAcquistoDAO = dl.getRichiestaAcquistoDAO();
        System.out.println(richiestaAcquistoDAO);
        /*
        List<RichiestaAcquisto> richieste = richiestaAcquistoDAO.getAllRichiesteAcquisto();
        System.out.println("CIAO SONO QUI");
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Test DAO");
        result.appendToBody("<ul>");
        for (RichiestaAcquisto richiesta : richieste) {
            System.out.println(richiesta);
            result.appendToBody("<li>" + HTMLResult.sanitizeHTMLOutput(richiesta.toString()) + "</li>");
        }
        result.appendToBody("</ul>");
        result.activate(request, response);

        */
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            //creiamo LOCALMENTE il data layer (da cui si accede ai DAO) e ci assicuriamo che venga chiuso alla fine della richiesta
            System.out.println("Mi trovo dentro processRequest");
            try (DataLayer dl = new WebmarketDataLayer(ds)) {
                dl.init();
                request.setAttribute("datalayer", dl);
                action_manipulate(request, response);
            }
        } catch (Exception ex) {
            ServletHelpers.handleError(ex, request, response, getServletContext());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Mi trovo dentro doGet");
        System.out.printf(request.getQueryString());
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //init data source (thread safe)
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/" + config.getServletContext().getInitParameter("data.source"));
        } catch (NamingException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return 
     */
    @Override
    public String getServletInfo() {
        return "A Manually-Crafted Data Model";
    }

}

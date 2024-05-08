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

import it.univaq.webmarket.framework.result.HTMLResult;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class TestMyDAO extends HttpServlet {

    private DataSource ds;
    private WebmarketDataLayer dl;

    private void action_manipulate(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {

        RichiestaAcquistoDAO richiestaAcquistoDAO = dl.getRichiestaAcquistoDAO();

        List<RichiestaAcquisto> richieste = richiestaAcquistoDAO.getAllRichiesteAcquisto();
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Test DAO");
        result.appendToBody("<ul>");
        for (RichiestaAcquisto richiesta : richieste) {
            result.appendToBody("<li>" + HTMLResult.sanitizeHTMLOutput(richiesta.toString()) + "</li>");
        }
        result.appendToBody("</ul>");
        result.activate(request, response);


    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            action_manipulate(request, response);
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
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
        try {
            action_manipulate(request, response);
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ds = (DataSource) getServletContext().getAttribute("datasource");
        try {
            // Forse esiste un modo migliore di farlo, senza utilizzare
            // come attributo di classe DataLayer
            this.dl = new WebmarketDataLayer(ds);
            this.dl.init();
        } catch (SQLException | DataException e) {
            throw new RuntimeException(e);
        }
        /*try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/" + config.getServletContext().getInitParameter("data.source"));
        } catch (NamingException ex) {
            throw new ServletException(ex);
        }*/
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

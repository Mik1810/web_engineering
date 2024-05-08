/*
 * TestMyDAO.java
 *
 * Questo esempio mostra come creare totalmente "a mano" un data model e i suoi DAO
 *
 * This example shows how to use manually-craft a data model and its DAOs
 *
 */
package it.univaq.webmarket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author Mik
 * @version 1.0
 */
public class IndexServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<head> <title> Index Servlet </title>" +
                "</head>" +
                "<body> " +
                "<h1>Index Servlet</h1> " +
                "<br/> " +
                "<a href='hello-servlet'>Hello Servlet</a> " +
                "<br> " +
                "<a href='coffee-servlet'>Make Coffee</a> " +
                "<br> " +
                "<a href='frameworktest'>Test DAOs</a> " +
                "</body> " +
                "</html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Returns a short description of the servlet.
     * @return A string representation of the servlet
     */
    @Override
    public String getServletInfo() {
        return "A Manually-Crafted Data Model";
    }

}

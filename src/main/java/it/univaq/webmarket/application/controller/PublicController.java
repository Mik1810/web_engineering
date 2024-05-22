/*
 * PublicController.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.RichiestaDAO;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.HTMLResult;

import java.io.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class PublicController extends ApplicationBaseController {

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {

        //preleviamo il data layer 
        //get the data layer
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        RichiestaDAO richiestaDAO = dl.getRichiestaAcquistoDAO();

        /*
        List<Article> articles = dl.getArticleDAO().getArticles();
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Show Articles");
        result.appendToBody("<h1>Articles available</h1>");
        if (!articles.isEmpty()) {
            result.appendToBody("<ul>");
            for (Article a : articles) {
                result.appendToBody("<li>"
                        + HTMLResult.sanitizeHTMLOutput(a.getTitle())
                        + "<br/><em>by " + a.getAuthor().getName() + " " + a.getAuthor().getSurname() + "</em>"
                        + "<br/><small>" + (a.getIssue() != null ? ("published on issue #" + a.getIssue().getNumber()) : "<em>unpublished</em>") + "</small>"
                        + "</li>");
            }
            result.appendToBody("</ul>");
            result.appendToBody("<p><a href=\"homepage\">Return to homepage</a></p>");

        }
        result.activate(request, response);
         */
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        action_default(request, response);
    }
}

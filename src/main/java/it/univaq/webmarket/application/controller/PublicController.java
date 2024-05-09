/*
 * PublicController.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.RichiestaAcquistoDAO;
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
        RichiestaAcquistoDAO richiestaAcquistoDAO = dl.getRichiestaAcquistoDAO();

        List<Richiesta> richieste = richiestaAcquistoDAO.getAllRichiesteAcquisto();
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Test DAO");
        result.appendToBody("<ul>");
        for (Richiesta richiesta : richieste) {
            result.appendToBody("<li>" + HTMLResult.sanitizeHTMLOutput(richiesta.toString()) + "</li>");
        }
        result.appendToBody("</ul>");

        // Lo faccio una seconda volta per vedere se la cache funziona
        List<Richiesta> richieste2 = richiestaAcquistoDAO.getAllRichiesteAcquisto();
        result.appendToBody("<b>Seconda richiesta per verificare la cache</b></br>");
        result.appendToBody("<ul>");
        for (Richiesta richiesta : richieste2) {
            result.appendToBody("<li>" + HTMLResult.sanitizeHTMLOutput(richiesta.toString()) + "</li>");
        }
        result.appendToBody("</ul>");

        result.activate(request, response);
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

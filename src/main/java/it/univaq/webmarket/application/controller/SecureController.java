/*
 * TestMyDAO.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.framework.data.DataException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class SecureController extends ApplicationBaseController {

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {

        //preleviamo il data layer 
        //get the data layer
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        //manipoliamo i dati usando le interfacce esposta dai DAO accessibili dal data layer
        //manipulate the data using the interfaces exposed by the DAOs accessible from the data layer
        /*
        int latest_number = dl.getIssueDAO().getLatestIssue().getNumber();
        //        
        Issue new_issue = dl.getIssueDAO().createIssue();
        new_issue.setNumber((latest_number + 1));
        new_issue.setDate(LocalDate.now());
        dl.getIssueDAO().storeIssue(new_issue);
        //        
        Article new_article = dl.getArticleDAO().createArticle();
        Author author = dl.getAuthorDAO().getAuthor(1); //assume it already exists
        if (author != null) {
            new_article.setAuthor(author);
            new_article.setTitle(SecurityHelpers.addSlashes("NEW ARTICLE FOR ISSUE " + (latest_number + 1)));
            new_article.setText(SecurityHelpers.addSlashes("article text"));
            new_article.setIssue(new_issue);
            dl.getArticleDAO().storeArticle(new_article);
            //
            Issue latest_issue = dl.getIssueDAO().getLatestIssue();
            List<Article> articles = latest_issue.getArticles();

            //mandiamo in output quanche informazione prelavata dal data model
            //output some information from the data model
            HTMLResult result = new HTMLResult(getServletContext());
            result.setTitle("Data Model Manipulation");
            result.appendToBody("<h1>Data Model Manipulation</h1>");
            result.appendToBody("<p>Original latest issue was #" + latest_number + "</p>");
            result.appendToBody("<p>Adding new issue #" + (latest_number + 1) + "</p>");
            result.appendToBody("<p>Adding article \"" + HTMLResult.sanitizeHTMLOutput(new_article.getTitle()) + "\" on the new issue</p>");
            result.appendToBody("<p>Fetching new latest issue:</p>");
            result.appendToBody("<p>Number: " + latest_issue.getNumber() + ", Date: " + latest_issue.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "</p>");
            if (!articles.isEmpty()) {
                result.appendToBody("<ul>");
                for (Article a : articles) {
                    result.appendToBody("<li>" + HTMLResult.sanitizeHTMLOutput(a.getTitle()) + "</li>");
                }
                result.appendToBody("</ul>");
            }
            result.appendToBody("<p><a href=\"homepage\">Return to homepage</a></p>");
            result.activate(request, response);
        } else {
            handleError("Cannot add article: undefined author", request, response);
        }*/
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        action_default(request, response);
    }
}

/*
 * HomepageController.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.framework.result.HTMLResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class HomepageController extends ApplicationBaseController {

    private void action_anonymous(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Welcome");
        result.appendToBody("<h1>Authentication required</h1>");
        //notare come passiamo alla servlet di login la nostra URL come referrer
        //note how we pass to the login servlet our URL as the referrer
        result.appendToBody("<p>Please <a href=\"login?referrer=" + URLEncoder.encode((String)request.getAttribute("thispageurl"), "UTF-8") + "\">Login</a></p>");
        result.appendToBody("<p>...or try to access</p><ul>");
        result.appendToBody("<li>the <a href='secured'>secured page</a></li>");
        result.appendToBody("<li>the <a href='public'>public page</a></li>");
        result.appendToBody("</ul>");

        result.activate(request, response);
    }

    private void action_logged(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Welcome back");
        Map<String, Object> li = (Map<String, Object>) request.getAttribute("logininfo");
        result.appendToBody("<h1>Welcome back, " +  li.get("username") + "</h1>");
        result.appendToBody("<p>You IP address is: " + li.get("ip") + "</p>");
        result.appendToBody("<p>Your connection started on: " + ((LocalDateTime) li.get("session-start-ts")).format(DateTimeFormatter.ISO_DATE_TIME) + "</p>");
        result.appendToBody("<p><a href=\"logout?referrer=" + URLEncoder.encode((String)request.getAttribute("thispageurl"), "UTF-8") + "\">Logout</a></p>");
        result.appendToBody("<p>Now you can access both</p><ul>");
        result.appendToBody("<li>the <a href='secured'>secured page</a></li>");
        result.appendToBody("<li>the <a href='public'>public page</a></li>");
        result.appendToBody("</ul>");

        result.activate(request, response);
    }

  
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession s = request.getSession(false);
        if (s == null) {
            action_anonymous(request, response);
        } else {
            action_logged(request, response);
        }

    }

}

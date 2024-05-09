/*
 * LoginController.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.HTMLResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class LoginController extends ApplicationBaseController {

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Welcome");
        result.appendToBody("<h1>Please Login</h1>");
        //
        if (request.getAttribute("https-redirect") != null) {
            result.appendToBody("<p>WARNING! you are not using a secured (https) connection! "
                    + "Please follow <a href=\"" + request.getAttribute("https-redirect") + "\">this url</a> to login securely!</p>");
        }
        //
        result.appendToBody("<form method=\"post\" action=\"login\">");
        result.appendToBody("<p>Username: <input name=\"email\" type=\"text\"/><br/><small>Hint: try &quot;michaelpiccirilli3@gmail.com&quot;</small></p>");
        result.appendToBody("<p>Password: <input name=\"password\" type=\"password\"/><br/><small>Hint: try &quot;supersafepassword&quot;</small></p>");
        if (request.getParameter("referrer") != null) {
            result.appendToBody("<input name=\"referrer\" type=\"hidden\" value=\"" + request.getParameter("referrer") + "\"/></p>");
        }
        result.appendToBody("<p><input value=\"login\" name=\"login\" type=\"submit\"/></p>");
        result.appendToBody("</form>");
        result.activate(request, response);
    }

    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        if (!email.isEmpty() && !password.isEmpty()) {
            Ordinante o = dl.getOrdinanteDAO().getOrdinanteByEmail(email);
            if (o != null && /*SecurityHelpers.checkPasswordHashPBKDF2(*/password.equals(o.getPassword())) {
                //se la validazione ha successo
                SecurityHelpers.createSession(request, email, o.getKey());
                //se è stato trasmesso un URL di origine, torniamo a quell'indirizzo
                if (request.getParameter("referrer") != null) {
                    response.sendRedirect(request.getParameter("referrer"));
                } else {
                    response.sendRedirect("homepage");
                }
            }
        } else {
            handleError("Login failed", request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getParameter("login") != null) {
            action_login(request, response);
        } else {
            String https_redirect_url = SecurityHelpers.checkHttps(request);
            request.setAttribute("https-redirect", https_redirect_url);
            action_default(request, response);
        }

    }

}

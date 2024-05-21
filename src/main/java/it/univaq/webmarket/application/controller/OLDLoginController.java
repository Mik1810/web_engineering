/*
 * LoginController.java
 *
 *
 */
package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.Amministratore;
import it.univaq.webmarket.data.model.Ordinante;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.HTMLResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class OLDLoginController extends ApplicationBaseController {

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
        result.appendToBody("<div class=\"radio-group\">" +
                "  <input type=\"radio\" id=\"amministratore\" name=\"tipologiaUtente\" value=\"Amministratore\">" +
                "  <label for=\"amministratore\">Amministratore</label></br>" +
                "  <input type=\"radio\" id=\"ordinante\" name=\"tipologiaUtente\" value=\"Ordinante\">" +
                "  <label for=\"ordinante\">Ordinante</label></br>" +
                "  <input type=\"radio\" id=\"tecnico_ordini\" name=\"tipologiaUtente\" value=\"TecnicoOrdini\">" +
                "  <label for=\"tecnico_ordini\">Tecnico Ordini</label></br>" +
                "  <input type=\"radio\" id=\"tecnico_preventivi\" name=\"tipologiaUtente\" value=\"TecnicoPreventivi\">" +
                "  <label for=\"tecnico_preventivi\">Tecnico dei Preventivi</label></br>" +
                "</div>");
        if (request.getParameter("referrer") != null) {
            result.appendToBody("<input name=\"referrer\" type=\"hidden\" value=\"" + request.getParameter("referrer") + "\"/></p>");
        }
        result.appendToBody("<p><input value=\"login\" name=\"login\" type=\"submit\"/></p>");
        result.appendToBody("</form>");
        System.out.println(request.getParameter("error"));
        if(request.getParameter("error") != null){
            result.appendToBody("<script>alert('Invalid email or password!')</script>");
        }
        result.activate(request, response);
    }

    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, DataException, NoSuchAlgorithmException, InvalidKeySpecException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tipologiaUtente = request.getParameter("tipologiaUtente");

        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");

        if (!email.isEmpty() && !password.isEmpty()) {
            switch (tipologiaUtente) {
                case "Ordinante":
                    Ordinante o = dl.getOrdinanteDAO().getOrdinanteByEmail(email);
                    if (o != null && /*SecurityHelpers.checkPasswordHashPBKDF2(*/password.equals(o.getPassword())) {
                        //se la validazione ha successo
                        //SecurityHelpers.createSession(request, email, o.getKey());
                        //se è stato trasmesso un URL di origine, torniamo a quell'indirizzo
                        if (request.getParameter("referrer") != null) {
                            System.out.println("Redirecting to: " + request.getParameter("referrer"));
                            response.sendRedirect(request.getParameter("referrer"));
                        } else {
                            response.sendRedirect("homepage");
                        }
                    } else { // Se l'email o la password sono errate
                        response.sendRedirect("login?error");
                    }
                case "Amministratore":
                    Amministratore a = dl.getAmministratoreDAO().getAmministratoreByEmail(email);
                    if (a != null && SecurityHelpers.checkPasswordHashPBKDF2(password, a.getPassword())) {
                        //se la validazione ha successo
                        //SecurityHelpers.createSession(request, email,a.getKey());
                        //se è stato trasmesso un URL di origine, torniamo a quell'indirizzo
                        if (request.getParameter("referrer") != null) {
                            System.out.println("Redirecting to: " + request.getParameter("referrer"));
                            response.sendRedirect(request.getParameter("referrer"));
                        } else {
                            response.sendRedirect("homepage");
                        }
                    } else { // Se l'email o la password sono errate

                        response.sendRedirect("login?error");
                    }
                case "TecnicoOrdini":
                case "TecnicoPreventivi":
                default:
                    handleError("Login failed", request, response);
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

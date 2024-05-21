package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.model.*;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import it.univaq.webmarket.framework.security.SecurityHelpers;
import it.univaq.webmarket.framework.utils.ServletHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends ApplicationBaseController {


    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ServletHelpers.printRequest(request);
        try {
            if (request.getParameter("login") != null) {
                handleLogin(request, response);
                System.out.println("Mi sto loggando...");
            } else {
                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                renderLoginPage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                Utente u = null;
                // Selezione del giusto utente
                switch (role) {
                    case "ordinante":
                        u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getOrdinanteDAO().getOrdinanteByEmail(email);
                        break;
                    case "tecnicoprev":
                        u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail(email);
                        break;
                    case "tecnicoord":
                        u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail(email);
                        break;
                    case "amministratore":
                        u = ((WebmarketDataLayer) request.getAttribute("datalayer")).getAmministratoreDAO().getAmministratoreByEmail(email);
                        break;
                    default:
                        handleError("Login failed", request, response);
                }

                if (u == null || ! SecurityHelpers.checkPasswordHashPBKDF2(password, u.getPassword())) {
                    response.sendRedirect("login?error");
                    return;
                }
                SecurityHelpers.createSession(request, email, u.getKey(), role);
                if (request.getParameter("referrer") != null) {
                    response.sendRedirect(request.getParameter("referrer"));
                }
                response.sendRedirect("test");
                System.out.println(u.getClass()+ ", " + u.getEmail()+ ", " + u.getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | DataException | IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                response.sendRedirect("login?error");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void renderLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        if(request.getParameter("error") != null) {
            datamodel.put("message_error", "Email o password errate!");
        }
        datamodel.put("referrer", request.getParameter("referrer"));
        result.activate("login.ftl.html", datamodel, response);
    }
}

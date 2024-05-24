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
import javax.servlet.http.HttpSession;
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
        //ServletHelpers.printRequest(request);
        try {
            if (request.getParameter("login") != null) {
                handleLogin(request, response);
            } else {
                String https_redirect_url = SecurityHelpers.checkHttps(request);
                request.setAttribute("https-redirect", https_redirect_url);
                renderLoginPage(request, response);
            }
        } catch (IOException | TemplateManagerException ex) {
            handleError(ex, request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        System.out.println("Role: " + role);

        // Mi prendo la sessione in caso esista già
        HttpSession s = request.getSession(false);
        // Se mi ero loggato con un ruolo, è scaduta la sessione ed ho cambiato ruolo
        if(s != null &&  s.getAttribute("role") != null && !s.getAttribute("role").equals(role)) {
            System.out.println("HERE");
            s.invalidate();
            response.sendRedirect("login?error=1");
            return;
        }

        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                Utente u = null;
                WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
                // Selezione del giusto utente
                switch (role) {
                    case "ordinante":
                        u = dl.getOrdinanteDAO().getOrdinanteByEmail(email);
                        break;
                    case "tecnicoprev":
                        u = dl.getTecnicoPreventiviDAO().getTecnicoPreventiviByEmail(email);
                        break;
                    case "tecnicoord":
                        u = dl.getTecnicoOrdiniDAO().getTecnicoOrdiniByEmail(email);
                        break;
                    case "amministratore":
                        u = dl.getAmministratoreDAO().getAmministratoreByEmail(email);
                        break;
                    default:
                        handleError("Login failed", request, response);
                }

                // Se la password è errata
                if (u == null || ! SecurityHelpers.checkPasswordHashPBKDF2(password, u.getPassword())) {
                    response.sendRedirect("login?error=2");
                    return;
                }

                // Creo la sessione
                SecurityHelpers.createSession(request, email, u.getKey(), role);

                // Se ha un referrer, lo reindirizzo lì
                if (request.getParameter("referrer") != null) {
                    response.sendRedirect(request.getParameter("referrer"));
                    return;
                }

                // Reindirizzo in base al ruolo
                switch (role) {
                    case "ordinante":
                        response.sendRedirect("ordinante");
                        break;
                    case "tecnicoprev":
                        response.sendRedirect("tecnicoprev");
                        break;
                    case "tecnicoord":
                        response.sendRedirect("tecnicoord");
                        break;
                    case "amministratore":
                        response.sendRedirect("admin");
                        break;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | DataException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //Se non sono presenti email o password
            response.sendRedirect("login?error=3");
        }
    }

    private void renderLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateManagerException {
        TemplateResult result = new TemplateResult(getServletContext());
        Map<String, Object> datamodel = new HashMap<>();
        if(request.getParameter("error") != null) {
            switch (request.getParameter("error")) {
                case "1":
                    datamodel.put("message_error", "Sessione scaduta, effettuare nuovamente il login!");
                    break;
                case "2":
                    datamodel.put("message_error", "Email o password errate!");
                    break;
                case "3":
                    datamodel.put("message_error", "Inserire email e password!");
                    break;
            }
        }
        datamodel.put("referrer", request.getParameter("referrer"));
        result.activate("login.ftl", datamodel, response);
    }
}
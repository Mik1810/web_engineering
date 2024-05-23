package it.univaq.webmarket.application.controller;

import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.framework.security.SecurityHelpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends ApplicationBaseController {

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityHelpers.disposeSession(request);
        if (request.getParameter("referrer") != null) {
            response.sendRedirect(request.getParameter("referrer"));
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            handleLogout(request, response);
        } catch (IOException ex) {
            handleError(ex, request, response);
        }
    }    
}

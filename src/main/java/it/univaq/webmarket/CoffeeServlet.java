package it.univaq.webmarket;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "coffeeServlet", value = "/coffee-servlet")
public class CoffeeServlet extends HttpServlet {

    private final String coffeeString = "Here is your coffee! \u2615";

    public void init() {
    }

    public void destroy() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<html><head>");
        out.println("<meta charset=\"UTF-8\"></head><body>");
        out.println("<p>" + coffeeString + "</p>");
        out.println("</body></html>");
    }
}

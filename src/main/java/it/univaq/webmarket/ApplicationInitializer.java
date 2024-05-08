package it.univaq.webmarket;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        DataSource ds = null;
        Pattern protect = null;

        //init protection pattern
        String p = event.getServletContext().getInitParameter("security.protect.patterns");
        if (p != null && !p.isBlank()) {
            String[] split = p.split("\\s*,\\s*");
            protect = Pattern.compile(Arrays.stream(split).collect(Collectors.joining("$)|(?:", "(?:", "$)")));
        } else {
            protect = Pattern.compile("a^"); //this regular expression does not match anything!
        }

        //init data source
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/" + event.getServletContext().getInitParameter("data.source"));
        } catch (NamingException ex) {
            Logger.getLogger(ApplicationInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        event.getServletContext().setAttribute("protect", protect);
        event.getServletContext().setAttribute("datasource", ds);
    }
}


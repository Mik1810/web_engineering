package it.univaq.webmarket.application.controller;

import freemarker.template.Configuration;
import it.univaq.webmarket.application.ApplicationBaseController;
import it.univaq.webmarket.application.WebmarketDataLayer;
import it.univaq.webmarket.data.DAO.StatiEnumDAO;
import it.univaq.webmarket.data.DAO.UfficioDAO;
import it.univaq.webmarket.data.model.TecnicoPreventivi;
import it.univaq.webmarket.data.model.Ufficio;
import it.univaq.webmarket.data.model.impl.proxy.TecnicoPreventiviProxy;
import it.univaq.webmarket.data.model.impl.proxy.UfficioProxy;
import it.univaq.webmarket.framework.data.DataException;
import it.univaq.webmarket.framework.result.HTMLResult;
import it.univaq.webmarket.framework.utils.EmailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TestController extends ApplicationBaseController {

    private void testEnums(WebmarketDataLayer dl) throws DataException {
        StatiEnumDAO statiEnumDAO = dl.getStatiEnumDAO();
        statiEnumDAO.getAllStatiConsegna().forEach(System.out::println);
        statiEnumDAO.getAllFeedback().forEach(System.out::println);
        statiEnumDAO.getAllStatiProposta().forEach(System.out::println);

        List.of(statiEnumDAO.getFeedback(1)).forEach(System.out::println);
        List.of(statiEnumDAO.getStatoConsegna(1)).forEach(System.out::println);
        List.of(statiEnumDAO.getStatoProposta(1)).forEach(System.out::println);

        System.out.println(dl.getCache().cache);
    }

    private void testUfficio(WebmarketDataLayer dl) throws DataException {
        UfficioDAO ufficioDAO = dl.getUfficioDAO();
        System.out.println("UfficioDAO testing: ");
        ufficioDAO.getAllUffici().forEach(System.out::println);
        System.out.println(ufficioDAO.getUfficio(1));
        Ufficio ufficio = new UfficioProxy(dl);
        ufficio.setNumeroUfficio(12);
        ufficio.setSede("Via sturzo");
        ufficio.setNumeroTelefono("0984333333");
        ufficio.setPiano(2);
        ufficio.setCitta("L'Aquila");
        ufficioDAO.storeUfficio(ufficio);

        dl.getCache().delete(Ufficio.class, ufficio.getKey());
        Ufficio u2 = ufficioDAO.getAllUffici()
                .stream()
                .filter(e -> e.getSede().equalsIgnoreCase("Via Sturzo"))
                .findFirst()
                .get();
        System.out.println("Ufficio 2: "+u2);
        u2.setSede("Via Ancona");
        ufficioDAO.storeUfficio(u2);

        ufficioDAO.deleteUfficio(u2);

        ufficioDAO.getAllUffici().forEach(System.out::println);
    }

    private void testEmail() throws IOException {
        EmailSender sender = (EmailSender) getServletContext().getAttribute("emailsender");

        sender.sendEmail("paoloccigiacomo@gmail.com", "Ciao sono michael");
        //sender.sendPDFWithEmail();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebmarketDataLayer dl = (WebmarketDataLayer) request.getAttribute("datalayer");
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Test");
        /*List.of(dl.getAmministratoreDAO().getAmministratoreByEmail("admin@gmail.com")).forEach(e -> result.appendToBody(e.toString() + "<br>"));
        dl.getOrdinanteDAO().getAllOrdinanti().forEach(e -> result.appendToBody(e.toString() + "<br>"));

        dl.getTecnicoOrdiniDAO().getAllTecnicoOrdini().forEach(e -> result.appendToBody(e.toString()+ "<br>"));
        System.out.println("AIuto");
        //Saving an ordinante
        TecnicoPreventivi tp = new TecnicoPreventiviProxy(dl);
        tp.setEmail("tptest1@gmail.com");
        tp.setPassword("tppassword");

        dl.getTecnicoPreventiviDAO().storeTecnicoPreventivi(tp);
        dl.getTecnicoPreventiviDAO().getAllTecnicoPreventivi().forEach(e -> result.appendToBody(e.toString()+ "<br>"));

        dl.getTecnicoPreventiviDAO().deleteTecnicoPreventivi(tp);
        result.appendToBody("<br>");
        dl.getTecnicoPreventiviDAO().getAllTecnicoPreventivi().forEach(e -> result.appendToBody(e.toString()+ "<br>"));*/

        //Testing ufficioDAO
        //testUfficio(dl);

        //Testing enums
        //testEnums(dl);

        testEmail();

        result.activate(request, response);
    }
}

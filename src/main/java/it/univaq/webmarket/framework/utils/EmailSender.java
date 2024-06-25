package it.univaq.webmarket.framework.utils;

import com.lowagie.text.DocumentException;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Proposta;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.RichiestaPresaInCarico;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaProxy;
import it.univaq.webmarket.framework.result.TemplateManagerException;
import it.univaq.webmarket.framework.result.TemplateResult;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailSender {

    public enum Event {
        RICHIESTA_REGISTRATA, // Mik
        RICHIESTA_PRESA_IN_CARICO, //Mik
        PROPOSTA_INSERITA, // Mik
        PROPOSTA_ACCETTATA, // MIK
        PROPOSTA_RIFIUTATA, // MIk
        ORDINE_CREATO, // Giacomo
        RICHIESTA_CHIUSA, // Giacomo
    }

    private String emailFrom, password;
    private Properties properties;

    public EmailSender(String emailFrom, String password, Properties properties) {
        this.emailFrom = emailFrom;
        this.password = password;
        this.properties = properties;
    }

    public void sendEmail(String to, String messageText) {
        // Uso un thread perchè potrebbe metterci del tempo ad inviare l'email
        new Thread(() -> {

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailFrom, password);
                }
            });

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(emailFrom));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Now set the actual message
                message.setText(messageText);

                // Send message
                Transport.send(message);
                Logger.getLogger(EmailSender.class.getName()).info("Sent message successfully....");
            } catch (MessagingException mex) {
                Logger.getLogger(EmailSender.class.getName()).severe(mex.getMessage());
            }
        }).start();
    }

    public void sendPDFWithEmail(ServletContext context, String to, Object obj, Event event) {
        try {
            TemplateResult result = new TemplateResult(context);
            String htmlresult = "";
            Map<String, Object> datamodel = new HashMap<>();
            Map<String, String> values = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            switch (event) {
                case RICHIESTA_REGISTRATA:
                    Richiesta richiesta = (Richiesta) obj;

                    datamodel = new HashMap<>();
                    datamodel.put("richiesta", richiesta);
                    htmlresult = result.activate("/pdf_templates/pdf_richiesta.ftl", datamodel, new StringWriter());

                    sb.append("Richiesta: ").append(richiesta.getCodiceRichiesta()).append("\n");
                    sb.append("Data: ").append(richiesta.getData()).append("\n");
                    sb.append("Ordinante: ").append(richiesta.getOrdinante().getEmail()).append("\n");
                    sb.append("Note: ").append(richiesta.getNote()).append("\n");
                    sb.append("Caratteristiche: ").append("\n");
                    for (CaratteristicaConValore ccv : richiesta.getCaratteristicheConValore()) {
                        sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
                    }

                    values.put("filename", "richiesta_" + richiesta.getCodiceRichiesta());
                    values.put("subject", "Richiesta: " + richiesta.getCodiceRichiesta());
                    values.put("text", sb.toString());

                    newEmailSender(context, to, htmlresult, values);
                    break;
                case RICHIESTA_PRESA_IN_CARICO:
                    RichiestaPresaInCarico richiestaPresaInCarico = (RichiestaPresaInCarico) obj;

                    datamodel.put("richiestaPresaInCarico", richiestaPresaInCarico);
                    htmlresult = result.activate("/pdf_templates/pdf_richiesta_presa_in_carico.ftl", datamodel, new StringWriter());

                    sb.append("Richiesta presa in carico").append("\n");
                    sb.append("Codice richiesta: ").append(richiestaPresaInCarico.getRichiesta().getCodiceRichiesta()).append("\n");
                    sb.append("Tecnico dei Preventivi: ").append(richiestaPresaInCarico.getTecnicoPreventivi().getEmail()).append("\n");
                    sb.append("\n");
                    sb.append("Richiesta: ").append(richiestaPresaInCarico.getRichiesta().getCodiceRichiesta()).append("\n");
                    sb.append("Data: ").append(richiestaPresaInCarico.getRichiesta().getData()).append("\n");
                    sb.append("Ordinante: ").append(richiestaPresaInCarico.getRichiesta().getOrdinante().getEmail()).append("\n");
                    sb.append("Note: ").append(richiestaPresaInCarico.getRichiesta().getNote()).append("\n");
                    sb.append("Caratteristiche: ").append("\n");
                    for (CaratteristicaConValore ccv : richiestaPresaInCarico.getRichiesta().getCaratteristicheConValore()) {
                        sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
                    }

                    values.put("filename", "richiesta_presa_in_carico_"+richiestaPresaInCarico.getRichiesta().getCodiceRichiesta());
                    values.put("subject", "Richiesta presa in carico: "+richiestaPresaInCarico.getRichiesta().getCodiceRichiesta());
                    values.put("text", sb.toString());

                    newEmailSender(context, to, htmlresult, values);

                    break;
                case PROPOSTA_INSERITA:
                    Proposta proposta = (Proposta) obj;

                    datamodel.put("proposta", proposta);
                    htmlresult = result.activate("/pdf_templates/pdf_proposta.ftl", datamodel, new StringWriter());

                    sb.append("Proposta accettata: ").append(proposta.getCodiceProdotto()).append("\n");
                    sb.append("Produttore: ").append(proposta.getProduttore()).append("\n");
                    sb.append("Nome Prodotto: ").append(proposta.getNomeProdotto()).append("\n");
                    sb.append("Prezzo: ").append(proposta.getPrezzo()).append("\n");
                    sb.append("Note: ").append(proposta.getNote()).append("\n");
                    sb.append("Stato Proposta: ").append(proposta.getStatoProposta()).append("\n");
                    sb.append("\n");
                    sb.append("Richiesta presa in carico da: ").append(proposta.getRichiestaPresaInCarico().getTecnicoPreventivi().getEmail()).append("\n");
                    sb.append("\n");
                    sb.append("Richiesta: ").append(proposta.getRichiestaPresaInCarico().getRichiesta().getCodiceRichiesta()).append("\n");
                    sb.append("Data: ").append(proposta.getRichiestaPresaInCarico().getRichiesta().getData()).append("\n");
                    sb.append("Ordinante: ").append(proposta.getRichiestaPresaInCarico().getRichiesta().getOrdinante().getEmail()).append("\n");
                    sb.append("Note: ").append(proposta.getRichiestaPresaInCarico().getRichiesta().getNote()).append("\n");
                    sb.append("Caratteristiche: ").append("\n");
                    for (CaratteristicaConValore ccv : proposta.getRichiestaPresaInCarico().getRichiesta().getCaratteristicheConValore()) {
                        sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
                    }

                    values.put("filename", "proposta_"+proposta.getCodiceProdotto());
                    values.put("subject", "Proposta: "+proposta.getCodiceProdotto());
                    values.put("text", sb.toString());

                    newEmailSender(context, to, htmlresult, values);
                    break;
                case PROPOSTA_ACCETTATA:
                    Proposta propostaAccettata = (Proposta) obj;

                    datamodel.put("proposta", propostaAccettata);
                    htmlresult = result.activate("/pdf_templates/pdf_proposta.ftl", datamodel, new StringWriter());

                    sb.append("Proposta creata con successo: ").append(propostaAccettata.getCodiceProdotto()).append("\n");
                    sb.append("Produttore: ").append(propostaAccettata.getProduttore()).append("\n");
                    sb.append("Nome Prodotto: ").append(propostaAccettata.getNomeProdotto()).append("\n");
                    sb.append("Prezzo: ").append(propostaAccettata.getPrezzo()).append("\n");
                    sb.append("Note: ").append(propostaAccettata.getNote()).append("\n");
                    sb.append("Stato Proposta: ").append(propostaAccettata.getStatoProposta()).append("\n");
                    sb.append("\n");
                    sb.append("Richiesta presa in carico da: ").append(propostaAccettata.getRichiestaPresaInCarico().getTecnicoPreventivi().getEmail()).append("\n");
                    sb.append("\n");
                    sb.append("Richiesta: ").append(propostaAccettata.getRichiestaPresaInCarico().getRichiesta().getCodiceRichiesta()).append("\n");
                    sb.append("Data: ").append(propostaAccettata.getRichiestaPresaInCarico().getRichiesta().getData()).append("\n");
                    sb.append("Ordinante: ").append(propostaAccettata.getRichiestaPresaInCarico().getRichiesta().getOrdinante().getEmail()).append("\n");
                    sb.append("Note: ").append(propostaAccettata.getRichiestaPresaInCarico().getRichiesta().getNote()).append("\n");
                    sb.append("Caratteristiche: ").append("\n");
                    for (CaratteristicaConValore ccv : propostaAccettata.getRichiestaPresaInCarico().getRichiesta().getCaratteristicheConValore()) {
                        sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
                    }

                    values.put("filename", "proposta_accettata_"+propostaAccettata.getCodiceProdotto());
                    values.put("subject", "Proposta accettata: "+propostaAccettata.getCodiceProdotto());
                    values.put("text", sb.toString());

                    newEmailSender(context, to, htmlresult, values);
                    break;
            }
        } catch (TemplateManagerException e) {
            Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());

        }

    }

    private void newEmailSender(ServletContext context, String to, String htmlresult, Map<String, String> values) {

        new Thread(() -> {
            String outputPdf = context.getRealPath("/WEB-INF/") + values.get("filename") + ".pdf";

            try {

                // Create a new document
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlresult);
                renderer.layout();

                // Write PDF to file
                FileOutputStream fos = new FileOutputStream(outputPdf);
                renderer.createPDF(fos);
                fos.close();

                // Creazione della sessione di posta
                Session session = Session.getInstance(properties, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, password);
                    }
                });

                // Messaggio da allegare all'email
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(this.emailFrom)); // Inserire il proprio indirizzo email
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // Inserire il destinatario
                message.setSubject(values.get("subject"));

                // Creazione del corpo del messaggio
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(values.get("text"));

                // Creazione della parte dell'allegato
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(outputPdf);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(values.get("filename") + ".pdf");

                // Composizione del messaggio
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentPart);

                // Impostazione del contenuto del messaggio
                message.setContent(multipart);

                // Invio del messaggio
                Transport.send(message);

                Logger.getLogger(EmailSender.class.getName()).info("Email inviata con successo!");

                // Cancella il file PDF dopo l'invio dell'email
                File pdfFile = new File(outputPdf);
                if (pdfFile.exists()) {
                    if (pdfFile.delete()) {
                        Logger.getLogger(EmailSender.class.getName()).info("File PDF eliminato con successo.");
                    } else {
                        Logger.getLogger(EmailSender.class.getName()).warning("Impossibile eliminare il file PDF.");
                    }
                } else {
                    Logger.getLogger(EmailSender.class.getName()).warning("File PDF non trovato durante la cancellazione.");
                }

            } catch (DocumentException | MessagingException | IOException e) {
                Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());
            }
        }).start();
    }
}

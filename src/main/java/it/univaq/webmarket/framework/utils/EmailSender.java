package it.univaq.webmarket.framework.utils;

import com.lowagie.text.DocumentException;
import it.univaq.webmarket.data.model.CaratteristicaConValore;
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
        RICHIESTA_REGISTRATA,
        RICHIESTA_PRESA_IN_CARICO,
        PROPOSTA_INSERITA,
        ORDINE_CREATO,
        RICHIESTA_CHIUSA
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

            Session session = Session.getInstance(properties,  new Authenticator() {
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

    /*
    * StringBuilder sb = new StringBuilder();
        sb.append("La seguente richiesta è stata presa in carico:").append("\n");
        sb.append("Richiesta: ").append(richiestaPresaInCarico.getRichiesta().getCodiceRichiesta()).append("\n");
        sb.append("Tecnico dei Preventivi: ").append(richiestaPresaInCarico.getTecnicoPreventivi().getEmail()).append("\n");
        String text = sb.toString();
        String subject = "Richiesta presa in carico: Richiesta" + richiestaPresaInCarico.getRichiesta().getCodiceRichiesta();
        String filename = "richiesta_presa_in_carico_ " + richiestaPresaInCarico.getRichiesta().getCodiceRichiesta() + ".pdf";
    * */

    public void sendPDFWithEmail(ServletContext context, String to, Object obj, Event event) {
        try {
            switch (event) {
                case RICHIESTA_REGISTRATA:
                    Richiesta richiesta = (RichiestaProxy) obj;

                    TemplateResult result = new TemplateResult(context);
                    Map<String, Object> datamodel = new HashMap<>();
                    datamodel.put("richiesta", richiesta);
                    String htmlresult = result.activate("/pdf_templates/pdf_richiesta.ftl", datamodel, new StringWriter());

                    Map<String, String> values = new HashMap<>();

                    StringBuilder sb = new StringBuilder();
                    sb.append("Richiesta: ").append(richiesta.getCodiceRichiesta()).append("\n");
                    sb.append("Data: ").append(richiesta.getData()).append("\n");
                    sb.append("Ordinante: ").append(richiesta.getOrdinante().getEmail()).append("\n");
                    sb.append("Note: ").append(richiesta.getNote()).append("\n");
                    sb.append("Caratteristiche: ").append("\n");
                    for (CaratteristicaConValore ccv : richiesta.getCaratteristicheConValore()) {
                        sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
                    }

                    values.put("filename", "richiesta_"+richiesta.getCodiceRichiesta());
                    values.put("subject", "Richiesta: "+richiesta.getCodiceRichiesta());
                    values.put("text", sb.toString());

                    newEmailSender(context, to, htmlresult, values);
                    break;
                case RICHIESTA_PRESA_IN_CARICO:
                    RichiestaPresaInCarico richiestaPresaInCarico = (RichiestaPresaInCarico) obj;
                    break;
                case PROPOSTA_INSERITA:
                    break;
            }
        } catch(TemplateManagerException e) {
            Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());

        }

    }

    private void newEmailSender(ServletContext context, String to, String htmlresult, Map<String, String> values) {

        new Thread(() -> {
            String outputPdf =  context.getRealPath("/WEB-INF/")+values.get("filename")+".pdf";

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
                attachmentPart.setFileName(values.get("filename")+".pdf");

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

            } catch(DocumentException | MessagingException | IOException e) {
                Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());
            }
        }).start();
    }
}

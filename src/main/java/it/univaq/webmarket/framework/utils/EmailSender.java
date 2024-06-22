package it.univaq.webmarket.framework.utils;

import it.univaq.webmarket.data.model.CaratteristicaConValore;
import it.univaq.webmarket.data.model.Richiesta;
import it.univaq.webmarket.data.model.impl.proxy.RichiestaProxy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailSender {

    public enum Event {
        REQUEST_REGISTERD,
        REQUEST_ACCEPTED,
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

    private void requestRegistered(String to, Richiesta richiesta) {

        // Creazione del documento PDF
        StringBuilder sb = new StringBuilder();
        sb.append("Richiesta: ").append(richiesta.getCodiceRichiesta()).append("\n");
        sb.append("Data: ").append(richiesta.getData()).append("\n");
        sb.append("Ordinante: ").append(richiesta.getOrdinante().getEmail()).append("\n");
        sb.append("Note: ").append(richiesta.getNote()).append("\n");
        sb.append("Caratteristiche: ").append("\n");
        for (CaratteristicaConValore ccv : richiesta.getCaratteristicheConValore()) {
            sb.append(ccv.getCaratteristica().getNome()).append(": ").append(ccv.getValore()).append("\n");
        }
        String text = sb.toString();
        String subject = "Richiesta creata con successo: " + richiesta.getCodiceRichiesta();
        String filename = "richiesta " + richiesta.getCodiceRichiesta() + ".pdf";

        new Thread(() -> {
            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                    String[] lines = text.split("\\r?\\n");
                    for (String line : lines) {
                        contentStream.showText(line);
                        contentStream.newLineAtOffset(0, -14); // Sposta verso il basso per la prossima linea
                    }
                    contentStream.endText();
                }
                document.save(filename);
                document.close();

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
                message.setSubject(subject);

                // Creazione del corpo del messaggio
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(text);

                // Creazione della parte dell'allegato
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(filename);

                // Composizione del messaggio
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentPart);

                // Impostazione del contenuto del messaggio
                message.setContent(multipart);

                // Invio del messaggio
                Transport.send(message);

                Logger.getLogger(EmailSender.class.getName()).info("Email inviata con successo!");
            } catch (MessagingException | IOException e) {
                Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());
            }
        }).start();
    }

    public void sendPDFWithEmail(String to, Object obj, Event event) {
        switch (event) {
            case REQUEST_REGISTERD:
                Richiesta richiesta = (RichiestaProxy) obj;
                requestRegistered(to, richiesta);
                break;
            case REQUEST_ACCEPTED:
                break;
        }

    }
}

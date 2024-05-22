package it.univaq.webmarket.framework.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailSender {

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
            // Get system properties
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp-mail.outlook.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

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

    public void sendPDFWithEmail() throws IOException {
        new Thread(() -> {
            try {
                // Creazione del documento PDF
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    contentStream.showText("Ciao sono michael");
                    contentStream.endText();
                }
                document.save( "hello_world.pdf");
                document.close();

                // Configurazione delle proprietà per la sessione di posta

                // Creazione della sessione di posta
                Session session = Session.getInstance(properties, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, password);
                    }
                });
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(this.emailFrom)); // Inserire il proprio indirizzo email
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("elisabetta.mecozzi0@gmail.com")); // Inserire il destinatario
                message.setSubject("File PDF con Hello World");

                // Creazione del corpo del messaggio
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText("Ciao, sono michael");

                // Creazione della parte dell'allegato
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource("hello_world.pdf");
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName("hello_world.pdf");

                // Composizione del messaggio
                MimeMultipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentPart);

                // Impostazione del contenuto del messaggio
                message.setContent(multipart);

                // Invio del messaggio
                Transport.send(message);

                Logger.getLogger(EmailSender.class.getName()).info("Email inviata con successo!");
            } catch (IOException | MessagingException e) {
                Logger.getLogger(EmailSender.class.getName()).severe(e.getMessage());
            }
        }).start();
    }
}

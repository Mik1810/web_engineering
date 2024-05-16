package it.univaq.webmarket.framework.utils;

import java.util.*;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletContext;

public class EmailSender {

    public void sendEmail(ServletContext context, String to, String messageText) {

        // Assuming you are sending email from localhost
        String emailSender = (String) context.getAttribute("email");
        String emailPassword = (String) context.getAttribute("password");

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp-mail.outlook.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties,  new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, emailPassword);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(emailSender));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            Logger.getLogger(EmailSender.class.getName()).info("Sent message successfully....");
        } catch (MessagingException mex) {
            Logger.getLogger(EmailSender.class.getName()).severe(mex.getMessage());
        }
    }
}

package com.tmi.emprendedores;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class testSendMail {
	public static void main(String... vars) {
		final String username = "UADE.EMPRENDEDORES@GMAIL.COM";
        final String password = "XXXXXX";
        final String destination = "XXXXXX";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destination)
            );
            message.setSubject("Emprendedores sabe donde vivis");
            message.setText("Alohaaaa"
                    + "\n\n ATTE: ATR.");

            System.out.println("Enviando...");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
        	System.out.println("Oops!");
            e.printStackTrace();
        }
		
	}
	
	
}

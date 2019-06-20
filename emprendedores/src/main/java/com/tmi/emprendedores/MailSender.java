package com.tmi.emprendedores;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmi.emprendedores.util.PathUtils;

public class MailSender {
	
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
	
	private static final String MAIL_CONFIG_FILE= "mailConfig.properties";
	
	private static String username;
	private static String password;
	
	private static final Properties mailProperties = new Properties();
	
	static {
		mailProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailProperties.put("mail.smtp.port", "587");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true"); //TLS
        
        try (InputStream input = new FileInputStream(PathUtils.CONFIG_PATH + MAIL_CONFIG_FILE)) {

            Properties prop = new Properties();
            prop.load(input);

            username = prop.getProperty("mail.username");
            password = prop.getProperty("mail.password");

        } catch (IOException ex) {
        	logger.error("NO SE PUDO RECUPERAR USER Y PASS DE CORREO! EXISTE EL ARCHIVO? > "+PathUtils.CONFIG_PATH + MAIL_CONFIG_FILE);
            ex.printStackTrace();
        }
	}
	
	public boolean sendMail(String destination, String subject, String content) throws AddressException{
        Session session = getSession(username, password);
        try {
        	logger.debug("Armando correo para "+destination);
            Message message = buildMessage(session, username, password, destination, subject, content); 
            logger.debug("Enviando correo a . "+destination);		
            Transport.send(message);
            logger.debug("OK Enviado correo a "+destination);

        } catch (MessagingException e) {
        	System.out.println("Oops!");
            e.printStackTrace();
        }
		return true;
	}

	private Session getSession(String username, String password) {
		Session mySession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		return mySession;
	}
	
	private Message buildMessage(Session session, String username, String password, String destination, String subject, String content) throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(destination)
        );
        message.setSubject(subject);
        message.setText(content);
        
        return message;
	}
	
	@PostConstruct
	public void getUserAndPass() {
		logger.debug(this.getClass().getSimpleName()+" instanciado, busco user y pass.");
		username = "UADE.EMPRENDEDORES@GMAIL.COM";
		password = "XXXXXX";
	}
}


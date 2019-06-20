package com.tmi.emprendedores;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

import com.tmi.emprendedores.controller.view.WebUtils;
import com.tmi.emprendedores.persistence.entities.RecuperoClave;
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
	
	public void sendMail(String destination, Message message) throws AddressException , MessagingException{
        logger.info("Enviando correo a . "+destination);		
        Transport.send(message);
        logger.info("OK Enviado correo a "+destination);
	}

	private Session getSession() {
		Session mySession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		return mySession;
	}
	
	public Message buildMessage(String destination, String subject, String content) throws AddressException, MessagingException {
		logger.debug("Armando correo para "+destination);
		Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(destination)
        );
        message.setSubject(subject);
        message.setText(content);
        
        return message;
	}
	
	public Message buildMessageRecuperoClave(String destination, RecuperoClave recupero) throws AddressException, MessagingException {
		String subject = recupero.getUsuario().getNick()+" solicitaste un cambio de clave?";
		
		String content = "Hola "+recupero.getUsuario().getNick()+"!\n"
				+ "\n"
				+"Nos llego un pedido de reseteo de clave. Si este fuiste vos por por favor seguí el link de abajo, de otra manera desestima este correo.\n"
				+"\n"
				+"http://emprendedores.ddns.net"+WebUtils.MAPPING_RECUPERO+"?id="+recupero.getIdUsuarioEncriptado()+"\n"
				+"\n"
				+"Saludos!\n"
				+"Equipo de Emprendedores.";
		
		Message message = buildMessage(destination, subject, content);
		
		return message;
	}
}


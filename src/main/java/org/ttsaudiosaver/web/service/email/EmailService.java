package org.ttsaudiosaver.web.service.email;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private static final Logger logger = Logger.getLogger(EmailService.class);
	
	private static final String PROP_SMTP_HOST = "mail.smtp.host";
	private static final String PROP_SMTP_AUTH = "mail.smtp.auth";
	private static final String PROP_SMTP_START_TLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String PROP_SMTP_PORT = "mail.smtp.port";
	
	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String SMTP_AUTH = "true";
	private static final String SMTP_START_TLS_ENABLE = "true";
	private static final String SMTP_PORT = "587";
	
	private static final String USERNAME = "ttsemailservicetest@gmail.com";
	private static final String PASSWORD = "!.0)7*K>3^|:7{q";
	
	private Session session;
	
	@PostConstruct
	private void init() {
		session = Session.getInstance(getProperties(), getAuthenticator());
	}
	
	private Authenticator getAuthenticator() {
		return new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		};
	}
	
	private Session getSession() {
		if(session == null) {
			init();
		}
		return session;
	}
	
	public void sendMessage(EmailMessage message) {
		try {
			Transport.send(getMessageToSend(message, getSession()));
		} catch(Exception e) {
			logger.error("Exception occured while sending an email: " + e.getMessage());
		}
	}
	
	public void sendMessages(Collection<EmailMessage> messages) {
		try {
			for(EmailMessage msg : messages) {
				Transport.send(getMessageToSend(msg, getSession()));
			}
		} catch(Exception e) {
			logger.error("Exception occured while sending an email: " + e.getMessage());
		}
	}
	
	private Properties getProperties() {
		Properties properties = new Properties();
		properties.put(PROP_SMTP_HOST, SMTP_HOST);
		properties.put(PROP_SMTP_AUTH, SMTP_AUTH);
		properties.put(PROP_SMTP_START_TLS_ENABLE, SMTP_START_TLS_ENABLE);
		properties.put(PROP_SMTP_PORT, SMTP_PORT);
		return properties;
	}
	
	private Message getMessageToSend(EmailMessage message, Session session) throws AddressException, MessagingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(USERNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
		msg.setSubject(message.getSubject());
		msg.setText((String) message.getContent());
		return msg;
	}
}
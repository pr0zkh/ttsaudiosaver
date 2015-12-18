package org.ttsaudiosaver.web.service.email;

public interface EmailMessage {
	
	public String getSubject();
	
	public Object getContent();
	
	public String getRecipient();

}
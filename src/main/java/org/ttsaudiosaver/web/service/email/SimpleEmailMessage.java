package org.ttsaudiosaver.web.service.email;

public class SimpleEmailMessage implements EmailMessage {
	
	private String subject;
	private String content;
	private String recipient;

	@Override
	public String getSubject() {
		return this.subject;
	}

	@Override
	public String getContent() {
		return this.content;
	}
	
	@Override
	public String getRecipient() {
		return recipient;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setContent(String content) {
		this.content = content;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
package edu.yang.trumpet.dmo;

import edu.yang.trumpet.model.Message;

public class MessagePair {
	private String userName;
	private Message message;
	public MessagePair(String userName, Message message) {
		super();
		this.userName = userName;
		this.message = message;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}

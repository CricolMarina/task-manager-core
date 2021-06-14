package com.stefanini.taskmanager.messagesender;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageReceiverImpl implements MessageReceiver {
	private static final Logger logger = LogManager.getLogger(MessageReceiverImpl.class);
	private String address;
	
	public MessageReceiverImpl(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void receive(Message message) {
		logger.info("New message : " + message.getText());
	}
}

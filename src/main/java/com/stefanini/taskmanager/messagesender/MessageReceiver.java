package com.stefanini.taskmanager.messagesender;

public interface MessageReceiver {
	/**
	 * This method is used print received message
	 * @param message
	 */
	void receive(Message message);
	
	String getAddress();
}

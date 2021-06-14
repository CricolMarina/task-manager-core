package com.stefanini.taskmanager.messagesender;

public interface MessageSender {
	
	/**
	 * This method is used to send message about new user added
	 * @param message
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	void sendMessage(Message message);
}
package com.stefanini.taskmanager.messagesender;

public interface MessageCreator {
	
	/**
	 * This method is used to create message template
	 * @param object
	 * @return message
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @return message
	 */
	String createMessage(Object object) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;
}

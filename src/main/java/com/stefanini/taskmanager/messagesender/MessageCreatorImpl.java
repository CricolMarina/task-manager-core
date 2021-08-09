package com.stefanini.taskmanager.messagesender;

import java.lang.reflect.Field;

import com.stefanini.taskmanager.annotations.Email;
import com.stefanini.taskmanager.annotations.EmailField;

public class MessageCreatorImpl implements MessageCreator{

	public String createMessage(Object object) throws IllegalArgumentException, IllegalAccessException {
//		String message = object.getClass().getAnnotation(Email.class).emailText();
//		for (Field field : object.getClass().getDeclaredFields()) {
//			field.setAccessible(true);
//			if (field.isAnnotationPresent(EmailField.class)) { 
//				message = message.replaceAll(field.getAnnotation(EmailField.class).fieldName(),String.valueOf(field.get(object)));
//			}
//		} 
		return "";
	}
}

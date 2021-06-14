package com.stefanini.taskmanager.annotations;

import java.lang.reflect.Field;

import com.stefanini.taskmanager.domain.User;

public class TestAnnotation {
	
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
		
	User user = new User();
	if(user.getClass().isAnnotationPresent(Email.class)) {
		System.out.println(user.getClass().getName());
		}
	for(Field field: user.getClass().getDeclaredFields()) {
		field.setAccessible(true);
		if (field.isAnnotationPresent(EmailField.class)) {
			try {
				System.out.println("Name" + field.getDeclaredAnnotation(Email.class) + field.get(user));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				}
			}
		}
	}
}


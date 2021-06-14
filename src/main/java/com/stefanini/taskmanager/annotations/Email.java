package com.stefanini.taskmanager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention (value = RetentionPolicy.RUNTIME)
public @interface Email {
	String emailText();
	}

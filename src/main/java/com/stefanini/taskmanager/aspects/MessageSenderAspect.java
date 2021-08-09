package com.stefanini.taskmanager.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.stefanini.taskmanager.messagesender.Message;
import com.stefanini.taskmanager.messagesender.MessageCreator;
import com.stefanini.taskmanager.messagesender.MessageCreatorImpl;
import com.stefanini.taskmanager.messagesender.MessageSender;
import com.stefanini.taskmanager.messagesender.MessageSenderImpl;

@Aspect
public class MessageSenderAspect {
	private MessageCreator creator = new MessageCreatorImpl();
	private MessageSender sender = new MessageSenderImpl();
	private static final Logger logger = LogManager.getLogger(MessageSenderAspect.class);

	@Pointcut("execution(public * com.stefanini.taskmanager.service.impl.UserServiceImpl.createUser(..))")
	public void callCreateUser() { }
	
	@Pointcut("execution(public * com.stefanini.taskmanager.service.impl.TasksServiceImpl.addTask(..))")
	public void callAddTask() {}
	
	@AfterReturning(pointcut = "callCreateUser()", returning = "user")
	public void afterCallUserCommand(Object user) {
		if (user!=null) {
			System.out.println("Executing sending message about new user created");
			try {
				String text = creator.createMessage(user);
				String address = "cricol.marina@extendaretail.com";
				Message message = new Message(text, address);
				sender.sendMessage(message);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	@AfterReturning(pointcut = "callAddTask()", returning = "task")
	public void afterCallTaskCommand(Object task) {
		System.out.println("Executing sending message about new task added");
			try {
				String text = creator.createMessage(task);
				String address = "cricol.marina@extendaretail.com";
				Message message = new Message(text, address);
				sender.sendMessage(message);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}


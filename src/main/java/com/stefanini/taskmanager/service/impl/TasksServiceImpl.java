package com.stefanini.taskmanager.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryTask;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.messagesender.Message;
import com.stefanini.taskmanager.messagesender.MessageCreator;
import com.stefanini.taskmanager.messagesender.MessageCreatorImpl;
import com.stefanini.taskmanager.messagesender.MessageSender;
import com.stefanini.taskmanager.messagesender.MessageSenderImpl;
import com.stefanini.taskmanager.service.TasksService;

public class TasksServiceImpl implements TasksService{
	
	private static final Logger logger = LogManager.getLogger(TasksServiceImpl.class);
	private TasksDAO dao;
	private MessageCreator creator = new MessageCreatorImpl();
	private MessageSender sender = new MessageSenderImpl();

	public TasksServiceImpl(AbstractFactoryTask daoFactory) {
		dao = daoFactory.getDAO(); 
	}

	public void addTask(Task task) {
		dao.addTask(task);
		try {
			String text = creator.createMessage(task);
			String address = "cricol.marina@extendaretail.com";
			Message message = new Message(text, address);
			sender.sendMessage(message);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error(e);
		}
	}
	
	public void showTaskByUser(String userName) {
		List<Task> taskList = dao.getTasksByUser(userName);
		for (Task task : taskList  ) {
			logger.info("Task title: " + task.getTitle() + " "
			+ "username: " + task.getUsername());
			}
		}
	}	
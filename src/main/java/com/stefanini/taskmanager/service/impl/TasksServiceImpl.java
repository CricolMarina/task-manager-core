package com.stefanini.taskmanager.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryTask;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.service.TasksService;

public class TasksServiceImpl implements TasksService{
	private static final Logger logger = LogManager.getLogger(TasksServiceImpl.class);
	private TasksDAO dao;

	public TasksServiceImpl(AbstractFactoryTask daoFactory) {
		dao = daoFactory.getDAO(); 
	}

	public Task addTask(Task task) {
		Task returnValue = null;
		try {
			dao.addTask(task);
			returnValue = task; 
		} catch (IllegalArgumentException | SecurityException e) {
			logger.error(e);
		}
		return returnValue;
	}
	
	public void showTaskByUser(String userName) {
		List<Task> taskList = dao.getTasksByUser(userName);
		for (Task task : taskList  ) {
			logger.info("Task title: " + task.getTitle() + " "
			+ "username: " + task.getUsername());
			}
		}
	}	
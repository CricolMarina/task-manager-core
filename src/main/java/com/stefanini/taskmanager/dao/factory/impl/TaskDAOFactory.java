package com.stefanini.taskmanager.dao.factory.impl;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryTask;
import com.stefanini.taskmanager.dao.impl.TasksDAOImpl;

public class TaskDAOFactory implements AbstractFactoryTask{

	public TasksDAO getDAO() {
		return TasksDAOImpl.getInstance();
		}
	}

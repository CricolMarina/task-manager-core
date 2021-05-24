package com.stefanini.taskmanager.dao.factory.impl;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryTask;
import com.stefanini.taskmanager.dao.hibernate.TaskDAOHibernate;

public class TaskDAOFactoryHibernate implements AbstractFactoryTask{
	
	public TasksDAO getDAO() {
		return TaskDAOHibernate.getInstance();
	}
}

package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.TasksDAO;

public interface AbstractFactoryTask {
	
	TasksDAO getDAO();

}

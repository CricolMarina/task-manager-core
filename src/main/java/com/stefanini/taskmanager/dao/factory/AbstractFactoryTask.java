package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.TasksDAO;

public interface AbstractFactoryTask {
	/**
	 * This method is used to get DAO
	 */
	TasksDAO getDAO();
}

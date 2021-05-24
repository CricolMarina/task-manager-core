package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.UserDAO;

public interface AbstractFactoryUser {
	/**
	 * This method is used to get DAO
	 */
	UserDAO getDAO();
}

package com.stefanini.taskmanager.dao.factory;

import com.stefanini.taskmanager.dao.UserDAO;

public interface AbstractFactoryUser {
	
	UserDAO getDAO();

}

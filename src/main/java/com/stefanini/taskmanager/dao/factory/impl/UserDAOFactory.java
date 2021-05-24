package com.stefanini.taskmanager.dao.factory.impl;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.dao.impl.UserDAOImpl;

public class UserDAOFactory implements AbstractFactoryUser{
	
	public UserDAO getDAO() {
		return UserDAOImpl.getInstance();
	}
}

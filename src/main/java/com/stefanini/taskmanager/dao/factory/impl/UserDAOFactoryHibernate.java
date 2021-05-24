package com.stefanini.taskmanager.dao.factory.impl;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.dao.hibernate.UserDAOHibernate;

public class UserDAOFactoryHibernate implements AbstractFactoryUser{
	
	public UserDAO getDAO() {
		return UserDAOHibernate.getInstance();
	}

}

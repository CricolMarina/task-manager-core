package com.stefanini.taskmanager.service.impl;
 
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO dao;
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl(AbstractFactoryUser daoFactory) {
		dao = daoFactory.getDAO();
	}
	
	/**
	 * This method is used to add users
	 * @param user 
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public void createUser(User user) {
		try {
			dao.createUser(user);
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.info("Alrealdy have this user in database");
		}
	}
	
	/**
	 * This method is used to show all users
	 */
	public void showAllUsers() {
		List<User> userList = dao.getUserList();
		logger.info("Show users' list ");
		for (User user : userList ) {
			logger.info(user);
		}
	}
}

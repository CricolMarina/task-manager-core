package com.stefanini.taskmanager.service;

import java.sql.SQLIntegrityConstraintViolationException;

import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.domain.User;

public interface UserService {
	/**
	 * This method is used to add users
	 * @param user
	 * @throws SQLIntegrityConstraintViolationException
	 */
	void createUser(User user);
		
	/**
	 * This method is used to show all users
	 */
	void showAllUsers();
	
	/**
	 * This method is used to get user by username
	 * @param username
	 * @return user
	 */
	User getUserByUsername(String username);
}


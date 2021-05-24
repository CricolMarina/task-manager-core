package com.stefanini.taskmanager.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.stefanini.taskmanager.domain.User;

public interface UserDAO {
	/**
	 * This method is used to create users.
	 * @param user 
	 * @throws SQLIntegrityConstraintViolationException
	 */
	void createUser(User user) throws SQLIntegrityConstraintViolationException ;
	
	/**
	 * This method is used to get users' list from data base.
	 * @return list of users
	 * 
	 */
	List<User> getUserList();
	
	/**
	 * 
	 * @param username
	 * @return
	 */
}



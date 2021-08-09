package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.dto.UserDTO;
import com.stefanini.taskmanager.exception.CreateUserException;

public interface UserService {
	/**
	 * This method is used to add users
	 * @param user
	 * @throws CreateUserFailedException 
	 * @return user
	 */
	UserDTO createUser(UserDTO userDTO) throws CreateUserException;
		
	/**
	 * This method is used to show all users
	 */
	void showAllUsers();
	
	/**
	 * This method is used to get user by username
	 * @param username
	 * @return user
	 */
	UserDTO getUserByUsername(String username);
	
	/**
	 * This method is used to get users' list
	 * @return list of users
	 */
	List<UserDTO> getUserList();
}


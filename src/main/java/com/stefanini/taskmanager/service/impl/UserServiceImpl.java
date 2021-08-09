package com.stefanini.taskmanager.service.impl;
 
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.dto.UserDTO;
import com.stefanini.taskmanager.exception.CreateUserException;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.util.MapperEntityToDTO;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO dao;
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(AbstractFactoryUser daoFactory) {
		dao = daoFactory.getDAO();
	}
	
	/**
	 * This method is used to add users and to send message with user's data
	 * @param userDTO 
	 * @throws SQLIntegrityConstraintViolationException
	 * @return userDTO
	 */
	public UserDTO createUser(UserDTO userDTO) throws CreateUserException{
		User returnValue = null;
		try {
			User user = MapperEntityToDTO.mapDtoToEntity(userDTO);
			dao.createUser(user);
			returnValue = user;
		} catch (SQLIntegrityConstraintViolationException e) {
			CreateUserException exception = new CreateUserException("Duplicate entry! Already have this user in database");
			exception.initCause(e);
			throw exception;
//			logger.error("Alrealdy have this user in database");
//		} catch (SecurityException | IllegalArgumentException e) {
//			logger.error(e);
		}
		return MapperEntityToDTO.mapEntityToDto(returnValue);
	}
		
	/**
	 * This method is used to show all users
	 */
	public void showAllUsers() {
		List<User> userList = dao.getUserList();
		logger.info("Show users' list ");
		for (User user : userList ) {
			user.getTasks();
			System.out.println(user);
		}
	}
	
	/**
	 * This method is used to get user by username
	 * @param username
	 * @return userDTO
	 */
	public UserDTO getUserByUsername(String username) {
		UserDTO userDTO = new UserDTO();
		User user = MapperEntityToDTO.mapDtoToEntity(userDTO);
		user = dao.getUserByUsername(username);	
		logger.info("Get user by username: " + user.getUsername());
		return MapperEntityToDTO.mapEntityToDto(user);
	}
	
	/**
	 * This method is user to get users' list
	 * @return userListDTO
	 */
	public List<UserDTO> getUserList(){
		List<UserDTO> userListDTO = new ArrayList<UserDTO>();
		List<User> userList = dao.getUserList();
		for (User user : userList) {
			userListDTO.add(MapperEntityToDTO.mapEntityToDto(user));
		}
		return userListDTO;
	}
}

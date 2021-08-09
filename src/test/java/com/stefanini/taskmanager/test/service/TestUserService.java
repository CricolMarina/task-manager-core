package com.stefanini.taskmanager.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.dto.UserDTO;
import com.stefanini.taskmanager.exception.CreateUserException;
import com.stefanini.taskmanager.service.impl.UserServiceImpl;
import com.stefanini.taskmanager.util.MapperEntityToDTO;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	@InjectMocks
	private UserServiceImpl userService = new UserServiceImpl();
	@Mock
	UserDAO userDAO ;

	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserService_createUser_CreationSuccess() throws CreateUserException, SQLIntegrityConstraintViolationException{
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("FirstName");
		userDTO.setLastName("LastName");
		userDTO.setUsername("username");
		userDTO = userService.createUser(userDTO);
		verify(userDAO).createUser(any(User.class));
		assertEquals("FirstName", userDTO.getFirstName());
	}
	
	@Test(expected = CreateUserException.class)
	public void testUserService_createUser_CreationException() throws CreateUserException, SQLIntegrityConstraintViolationException {
		UserDTO userDTO = new UserDTO();
		doThrow(SQLIntegrityConstraintViolationException.class)
		.when(userDAO)
		.createUser(any(User.class));
		userService.createUser(userDTO);
	}
	
	@Test
	public void testUserService_getUserByUsername_Succes() {
		UserDTO userDTO = new UserDTO("Marina", "Cricol", "mina");
		User user = MapperEntityToDTO.mapDtoToEntity(userDTO);
		Mockito.when(userDAO.getUserByUsername("mina")).thenReturn(user);
		userDTO = userService.getUserByUsername(userDTO.getUsername());
		
		verify(userDAO).getUserByUsername(user.getUsername());
		assertEquals(userDTO.getUsername(), (userDAO.getUserByUsername(userDTO.getUsername())).getUsername());
	}
	
	@Test
	public void testUserService_showAllUsers_Success() {
		userService.showAllUsers();
		verify(userDAO).getUserList();
	}
}

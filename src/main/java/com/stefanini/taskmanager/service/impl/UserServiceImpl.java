package com.stefanini.taskmanager.service.impl;
 
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryUser;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.messagesender.Message;
import com.stefanini.taskmanager.messagesender.MessageCreator;
import com.stefanini.taskmanager.messagesender.MessageCreatorImpl;
import com.stefanini.taskmanager.messagesender.MessageReceiver;
import com.stefanini.taskmanager.messagesender.MessageReceiverImpl;
import com.stefanini.taskmanager.messagesender.MessageSender;
import com.stefanini.taskmanager.messagesender.MessageSenderImpl;
import com.stefanini.taskmanager.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO dao;
	private MessageCreator creator = new MessageCreatorImpl();
	private MessageSender sender = new MessageSenderImpl();
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl(AbstractFactoryUser daoFactory) {
		dao = daoFactory.getDAO();
	}
	
	/**
	 * This method is used to add users and to send message with user's data
	 * @param user 
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public void createUser(User user) {
		try {
			dao.createUser(user);
			String text = creator.createMessage(user);
			String address = "cricol.marina@extendaretail.com";
			Message message = new Message(text, address);
			sender.sendMessage(message);
			
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.info("Alrealdy have this user in database");
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
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
	
	/**
	 * This method is used to get used by username
	 * @param username
	 * @return user
	 */
	public User getUserByUsername(String username) {
		User user = dao.getUserByUsername(username);	
		logger.info("Get user by username: " + user.getUsername());
		return user;
	}
}

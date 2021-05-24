package com.stefanini.taskmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.AbstractManagerConnection;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.domain.User;

public class UserDAOImpl extends AbstractManagerConnection implements UserDAO{
	
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	
	private UserDAOImpl() {}
	
	private static UserDAOImpl userDAO;
	
	public static UserDAOImpl getInstance() {
		if (userDAO==null) {
		userDAO = new UserDAOImpl();
	}
		return userDAO;
	}
	
	/**
	 * This method is used to add users
	 * @param user 
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public void createUser(User user) throws SQLIntegrityConstraintViolationException {
		logger.info("Add user " + user.getFirstName());
		try {
			Connection connection = getConnection();
		    // Statement
		    String createUser="INSERT INTO users (username, first_name, last_name)" + "VALUES (?, ?, ?)";
		    PreparedStatement stmt = connection.prepareStatement(createUser);
		    
		    stmt.setString(1, user.getUsername());
		    stmt.setString(2, user.getFirstName());
		    stmt.setString(3, user.getLastName());
		    stmt.execute();
		    stmt.close();
		    
		} catch (SQLIntegrityConstraintViolationException e) {
			//Check if user duplication
			if(e.getMessage().contains("Duplicate entry")) {
				throw e;
			} else {
				logger.error("Error", e);
			}
		}
		catch (Exception e) {
			logger.error("Error", e);
		}
	}
	
	/**
	 * This method is used to get users' list
	 *@return users
	 */
	public List<User> getUserList() {
		List<User> users = new ArrayList<User>();
		
		try {
			Connection connection = getConnection();

			String sqlRequest = "select username, first_name, last_name, count(tasks.task_id) as tasks from users "
					+ "inner join tasks on users.username=tasks.user \r\n"
					+ "group by user;";
			PreparedStatement stmt = connection.prepareStatement(sqlRequest);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
			    user.setUsername(rs.getString("username"));
			    user.setFirstName(rs.getString("first_name"));
			    user.setLastName(rs.getString("last_name"));
			    user.setTasksCounter(rs.getInt("tasks"));
			    users.add(user);
			}
			stmt.close();
		}
			catch (Exception e) {
				logger.error("Error", e);
			}
		    return users;
		}
}
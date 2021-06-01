package com.stefanini.taskmanager.dao.hibernate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.util.HibernateSessionManager;

public class UserDAOHibernate implements UserDAO{
	
	private static final Logger logger = LogManager.getLogger(UserDAOHibernate.class);
	private static UserDAOHibernate userDAO;

	private UserDAOHibernate() {}
		
	public static UserDAOHibernate getInstance() {
		if (userDAO==null) {
			userDAO = new UserDAOHibernate();
		}
		return userDAO;
	}
	
	/**
	 * This method is used to create users
	 * @param user
	 */
	public void createUser(User user) throws SQLIntegrityConstraintViolationException {
		logger.info("Add user " + user.getFirstName());
		
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
	        Transaction tx1 = session.beginTransaction();
	        session.save(user);
	        tx1.commit();
	        session.close();
	        } 
		catch (ConstraintViolationException e) {
			//Check if user duplication
			if(e.getSQLException().getMessage().contains("Duplicate entry")) {
				throw (SQLIntegrityConstraintViolationException)e.getSQLException();
			} else {
				logger.error("Error", e);
				}
			}
		}

	/**
	 * This method is used to get user's list
	 * @return users
	 */
	public List<User> getUserList() {
		List<User> users = (List<User>) HibernateSessionManager.getSessionFactory()
				.openSession().createQuery("From User").list();
        return users;
        } 
	
	/** 
	 * This method is used to get user by username
	 * @param username
	 * @return user
	 */
	public User getUserByUsername(String username) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		Query query = session.createQuery("FROM User where username =: username")
        		.setParameter("username", username);
		User user = (User) query.uniqueResult();
		logger.info("Get user by username:" + user.getUsername());
		return user;
		}
	}



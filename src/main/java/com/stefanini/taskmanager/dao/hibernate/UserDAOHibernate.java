package com.stefanini.taskmanager.dao.hibernate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
//		catch (SQLIntegrityConstraintViolationException e) {
//			//Check if user duplication
//			if(e.getMessage().contains("Duplicate entry")) {
//				throw e;
//			} else {
//				LOG.error("Error", e);
//			}
//		}
		catch (Exception e) {
			logger.error("Error", e);
		}
}

	/**
	 * This method is used to get user's list
	 */
	
	public List<User> getUserList() {
		List<User> users = (List<User>) HibernateSessionManager.getSessionFactory()
				.openSession().createQuery("From User").list();
        return users;
	} 
	
//	public User getByUsername(String username) {
//		Session session = HibernateSessionManager.getSessionFactory().openSession();
//        Query query = session.createQuery("FROM user where username =: username")
//        		.setParameter("username", username);
//        return (User) query.uniqueResult();
//	}
	
}    


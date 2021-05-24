package com.stefanini.taskmanager.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.util.HibernateSessionManager;

public class TaskDAOHibernate implements TasksDAO{
	private static final Logger logger = LogManager.getLogger(UserDAOHibernate.class);


	private static TaskDAOHibernate userDAO;

	private TaskDAOHibernate() {}
		
	public static TaskDAOHibernate getInstance() {
		if (userDAO==null) {
			userDAO = new TaskDAOHibernate();
		}
		return userDAO;
	}
	
	/**
	 * This method is used to add tasks
	 * @param task
	 */
	public void addTask(Task task) {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User where username =: username")
        		.setParameter("username", task.getUsername());
        User user = (User) query.uniqueResult();
        if(user != null) {
        	task.setUser(user);
        	session.save(task);
        } else {
        logger.error("No user with user name " + task.getUsername() + "found in the DB!");
        }
        session.close();
    }
	
	/**
	 * This method is used to get a task list by useraname
	 * @param userName
	 * @return list of tasks
	 */
	public List<Task> getTasksByUser(String username){
        Session session = HibernateSessionManager.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task where username =: username")
        		.setParameter("username", username);
        return query.list();
	}
}

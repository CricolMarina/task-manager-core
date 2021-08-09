package com.stefanini.taskmanager.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.util.HibernateSessionManager;

@Repository
public class TaskDAOHibernate implements TasksDAO{
	
	private static final Logger logger = LogManager.getLogger(TaskDAOHibernate.class);

	private static TaskDAOHibernate userDAO;
	
	public TaskDAOHibernate() {}
		
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
        if(task.getUser() != null) {
        	session.save(task);
        } else {
        	logger.error("No user with username " + task.getUsername() + "found in the DB!");
        	}
        session.close();
        }
	
	/**
	 * This method is used to get task list by username
	 * @param userName
	 * @return list of tasks
	 */
	public List<Task> getTasksByUser(String username){
        Session session = HibernateSessionManager.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task where username =: username")
        		.setParameter("username", username);
        return query.list();
	}
	
	public List<Task> getTaskList(){
		List<Task> taskList = (List<Task>) HibernateSessionManager.getSessionFactory()
				.openSession().createQuery("From Task").list();
        return taskList;
	}
}

package com.stefanini.taskmanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stefanini.taskmanager.dao.AbstractManagerConnection;
import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.domain.Task;

public class TasksDAOImpl extends AbstractManagerConnection implements TasksDAO {
	
	private static final Logger logger = LogManager.getLogger(TasksDAOImpl.class);
	private static TasksDAOImpl taskDAO;

	private TasksDAOImpl() {}
	
	public static TasksDAOImpl getInstance() {
		if (taskDAO==null) {
		taskDAO = new TasksDAOImpl();
	}
		return taskDAO;
	}
	
	/**
	 * This method is used to add tasks for users
	 * @param task
	 */
	public void addTask(Task task) {
		logger.info("Add task " + task.getTitle());
		try {
			Connection connection = getConnection();
		    // Statement
		    String addTask="INSERT INTO task (username, task_title, task_description)\r\n"
		    		+ "VALUES (?, ?, ?)";
		    PreparedStatement stmt = connection.prepareStatement(addTask);
		    
		    stmt.setString(1, task.getUsername());
		    stmt.setString(2, task.getTitle());
		    stmt.setString(3, task.getDescription());
		    stmt.execute();
		    stmt.close();
	
		} catch (Exception e) {
			logger.error("Error", e);
		}
	}
	
	/**
	 * This method is used to get task by userName
	 * @param userName
	 * @return list of tasks
	 */
	public List<Task> getTasksByUser(String userName){
		List<Task> tasksList = new ArrayList<Task>();
		try {
			Connection connection = getConnection();
			
			String sqlRequest = "SELECT * FROM task WHERE username = ?";
			PreparedStatement stmt = connection.prepareStatement(sqlRequest);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("task_id"));
			    task.setUserame(rs.getString("user"));
			    task.setTitle(rs.getString("task_title"));
			    task.setDescription(rs.getString("task_description"));
			    tasksList.add(task);
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Error", e);
			}
		return tasksList;
		}  
	}
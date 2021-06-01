package com.stefanini.taskmanager.dao;

import java.util.List;

import com.stefanini.taskmanager.domain.Task;

public interface TasksDAO {
		/**
		 * This method is used to add tasks
		 * @param task
		 * 
		 * */
		void addTask(Task task);
		
		/**
		 * This method is used to get by user the list of tasks
		 * @param userName
		 * @return list of tasks 
		 */
		List<Task> getTasksByUser(String userName);
		}

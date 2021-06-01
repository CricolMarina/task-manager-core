package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.domain.Task;

public interface TasksService {
	/**
	 * This method is used to add tasks for users
	 * @param task
	 */
	void addTask(Task task);
	
	/**
	 * This method is used to show the user's task by userName
	 * @param userName
	 */
	void showTaskByUser(String userName);
	}


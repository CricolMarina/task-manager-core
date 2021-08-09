package com.stefanini.taskmanager.service;

import java.util.List;

import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.dto.TaskDTO;
import com.stefanini.taskmanager.exception.AddTaskException;

public interface TasksService {
	/**
	 * This method is used to add tasks for users
	 * @param task
	 * @throws AddTaskException 
	 */
	TaskDTO addTask(TaskDTO taskDTO) throws AddTaskException;
	
	/**
	 * This method is used to show the user's task by userName
	 * @param userName
	 */
	void showTaskByUser(String userName);
	
	List<TaskDTO> getTaskList();
}


package com.stefanini.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.dao.factory.AbstractFactoryTask;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.dto.TaskDTO;
import com.stefanini.taskmanager.dto.UserDTO;
import com.stefanini.taskmanager.exception.AddTaskException;
import com.stefanini.taskmanager.service.TasksService;
import com.stefanini.taskmanager.service.UserService;
import com.stefanini.taskmanager.util.MapperEntityToDTO;

@Service
@Transactional
public class TasksServiceImpl implements TasksService{
	private static final Logger logger = LogManager.getLogger(TasksServiceImpl.class);
	@Autowired
	private TasksDAO dao;
	@Autowired
	private UserDAO userDAO;
	public TasksServiceImpl() {}

	public TasksServiceImpl(AbstractFactoryTask daoFactory) {
		dao = daoFactory.getDAO(); 
	}

	public TaskDTO addTask(TaskDTO taskDTO) throws AddTaskException {
		Task returnValue = null;
		try {
			Task task = MapperEntityToDTO.mapDtoToEntity(taskDTO);
			User user = userDAO.getUserByUsername(taskDTO.getUsername());
			if(user!=null) {
				task.setUser(user);
				dao.addTask(task);
				returnValue = task;
				logger.info("Task added to user : " + returnValue.getUsername());
			} else {
				logger.info("No user with such username " + task.getUsername());
				throw new AddTaskException ("No user with"+ task.getUsername() + "username was found in database!");
				}
			} catch (IllegalArgumentException | SecurityException e) {
				logger.error(e);
			}
		return MapperEntityToDTO.mapEntityToDTO(returnValue);
	}
	
	public void showTaskByUser(String userName) {
		List<Task> taskList = dao.getTasksByUser(userName);
		for (Task task : taskList  ) {
			logger.info("Task title: " + task.getTitle() + " "
			+ "username: " + task.getUser().getUsername());
		}
	}
	
	public List<TaskDTO> getTaskList(){
		List<TaskDTO> taskListDTO = new ArrayList<TaskDTO>();
		List<Task> taskList = dao.getTaskList();
		for(Task task : taskList) {
			taskListDTO.add(MapperEntityToDTO.mapEntityToDTO(task));
		}
		return taskListDTO;
	}
	
}	
package com.stefanini.taskmanager.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.stefanini.taskmanager.dao.TasksDAO;
import com.stefanini.taskmanager.dao.UserDAO;
import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.domain.User;
import com.stefanini.taskmanager.dto.TaskDTO;
import com.stefanini.taskmanager.exception.AddTaskException;
import com.stefanini.taskmanager.service.impl.TasksServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestTaskService {
	
	@InjectMocks
	private TasksServiceImpl taskService = new TasksServiceImpl();
	@Mock
	TasksDAO taskDAO;
	@Mock
	UserDAO userDAO;
	
	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testTaskService_addTask_Success() throws AddTaskException {
		TaskDTO taskDTO = new TaskDTO("minulea", "task_title", "task_description");
		User user = new User("Marina", "Cricol", "minulea");
		Mockito.when(userDAO.getUserByUsername("minulea")).thenReturn(user);
		taskDTO = taskService.addTask(taskDTO);
		assertEquals("task_title", taskDTO.getTitle());
		assertEquals("task_description", taskDTO.getDescription());
		assertEquals("minulea", taskDTO.getUsername());
	}
	
	@Test
	public void testTaskService_ShowTaskByUser_Success() {
		List<Task> taskList = new ArrayList<Task>();
		Mockito.when(taskDAO.getTasksByUser("searcher")).thenReturn(taskList);
		taskService.showTaskByUser("searcher");
		verify(taskDAO).getTasksByUser("searcher");

	}
	
	@Test
	public void testTaskService_GetTaskList_Success() {
		List<Task> taskList = new ArrayList<Task>();
		Task task = new Task(new User("Pavel", "Cricol", "searcher"), "tt", "td");
		taskList.add(task);
		Mockito.when(taskDAO.getTaskList()).thenReturn(taskList);
		List<TaskDTO> taskListDTO =  taskService.getTaskList();
		assertEquals(taskListDTO.get(0).getTitle(), taskList.get(0).getTitle());
		assertEquals(taskListDTO.get(0).getDescription(), taskList.get(0).getDescription());
		assertEquals(taskListDTO.get(0).getUsername(), taskList.get(0).getUser().getUsername());
	}
}

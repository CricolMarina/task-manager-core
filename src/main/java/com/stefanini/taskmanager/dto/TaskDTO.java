package com.stefanini.taskmanager.dto;

import com.stefanini.taskmanager.annotations.Email;
import com.stefanini.taskmanager.annotations.EmailField;
import com.stefanini.taskmanager.domain.User;

@Email(emailText = "Task {taskTitle} {taskDescription} has been assigned to {username}")
public class TaskDTO {
	
	@EmailField(fieldName =  "username")
	private String username;
	@EmailField(fieldName = "taskTitle")
	private String title;
	@EmailField(fieldName =  "taskDescription")
	private String description;
	
	public TaskDTO() {}

	public TaskDTO(String username, String title, String description) {
		this.username = username;
		this.title = title;
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "TaskDTO [username=" + username + ", title=" + title + ", description=" + description + "]";
	}
}

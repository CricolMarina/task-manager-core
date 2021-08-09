package com.stefanini.taskmanager.dto;

import com.stefanini.taskmanager.annotations.Email;
import com.stefanini.taskmanager.annotations.EmailField;

@Email(emailText = "User {firstName} / {lastName} identified by username {userName} has been created!")
public class UserDTO {
	
	@EmailField(fieldName = "firstName")
	private String firstName;
	@EmailField(fieldName = "lastName")
	private String lastName;
	@EmailField(fieldName = "userName") 
	private String username;
	
	public UserDTO() {}

	public UserDTO(String firstName, String lastName, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User {" + 
				"username = ' " + username + '\'' +
				",firstName = " + firstName + 
				",lastName=' " + lastName+ '\'' +
				'}';
	}
}

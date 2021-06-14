package com.stefanini.taskmanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.stefanini.taskmanager.annotations.Email;
import com.stefanini.taskmanager.annotations.EmailField;

@Entity 
@Table (name = "user")
@Email(emailText = "User {firstName} / {lastName} identified by username {userName} has been created!")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "first_name")
	@EmailField(fieldName = "firstName")
	private String firstName;
	@Column (name = "last_name")
	@EmailField(fieldName = "lastName")
	private String lastName;
	@Column (name = "username")
	@EmailField(fieldName = "userName") 
	private String username;
	
	@Transient
	private int tasksCounter;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<Task>(); 
	
	public User () {
	} 
	/**
	 * Creates an user with the specified parameters
	 * @param firstName
	 * @param lastName
	 * @param username
	 */
	public User( String firstName, String lastName,String username) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.username=username;
	}
	
	public void addTask(Task task) {
		task.setUser(this);
		tasks.add(task);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	
	public int getTasksCounter() {
		return tasksCounter;
	}
	
	public void setTasksCounter(int tasksCounter) {
		this.tasksCounter = tasksCounter;
	}
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks=tasks;
	}
	
	@Override
	public String toString() {
		return "Users{" + 
				"username = ' " + username + '\'' +
				",firstName = " + firstName + 
				",lastName=' " + lastName+ '\'' +
				",tasksCounter= " + tasksCounter +
				'}';
		}
	}


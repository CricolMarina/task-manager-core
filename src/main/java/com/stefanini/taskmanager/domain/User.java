package com.stefanini.taskmanager.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity 
@Table (name = "user")
@SecondaryTable (name = "task")

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "username")
	private String username;
	@Column (name = "first_name")
	private String firstName;
	@Column (name = "last_name")
	private String lastName;
	
	@Transient
	private int tasksCounter;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;
	
	public User () {
	} 
	
	public User( String firstName, String lastName,String username) {
		this.username=username;
		this.firstName=firstName;
		this.lastName=lastName;
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
	
	public void setTasks() {
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


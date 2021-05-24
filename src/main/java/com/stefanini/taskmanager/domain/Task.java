package com.stefanini.taskmanager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "task")
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private String username;
	
	@Column(name = "task_title")
	private String title;
	
	@Column(name = "task_description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	public Task() {}
	/**
	 * Creates a task with the specified parameters
	 * @param username
	 * @param title
	 * @param description
	 */
	public Task(String username, String title, String description) {
		this.username=username;
		this.title=title;
		this.description=description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUserame(String username) {
		this.username= username;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	@Override
	public String toString() {
		return "Tasks{" + 
		"id=' " + id + '\'' +
		", user=" + user + 
		", tile=' " + title + '\'' + 
		", description = " + description +
		'}';
		
	}
}

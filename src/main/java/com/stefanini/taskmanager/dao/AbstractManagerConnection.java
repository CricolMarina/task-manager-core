package com.stefanini.taskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractManagerConnection {
	
	private String url ="jdbc:mysql://localhost:3306/task_manager";
	private String dbuser= "root";
	private String password = "root";
	
	public AbstractManagerConnection() {}
	
	/**
	 * Creates connection with the specified parameters
	 * @param url
	 * @param dbuser
	 * @param password
	 */
	public AbstractManagerConnection(String url, String dbuser, String password) {
		this.url=url;
		this.dbuser=dbuser;
		this.password=password;
	}

	/**
	 * This method is used to establish connection with database 
	 * @return connection
	 * @throws ReflectiveOperationException
	 * @throws SQLException
	 * @return connection
	 */
	protected Connection getConnection() throws ReflectiveOperationException, SQLException {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url, dbuser, password);
				return connection;
				}
	}

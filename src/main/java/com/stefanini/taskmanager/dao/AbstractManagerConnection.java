package com.stefanini.taskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractManagerConnection {
	
	private static final Logger logger = LogManager.getLogger(AbstractManagerConnection.class);

	private String url ="jdbc:mysql://localhost:3306/task_manager";
	private String dbuser= "root";
	private String password = "root";
	
	public AbstractManagerConnection() {}
	
	public AbstractManagerConnection(String url, String dbuser, String password) {
		this.url=url;
		this.dbuser=dbuser;
		this.password=password;
	}


	protected Connection getConnection() throws ReflectiveOperationException, SQLException {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url, dbuser, password);
				return connection;
	}
	
}

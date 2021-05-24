package com.stefanini.taskmanager.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.stefanini.taskmanager.domain.Task;
import com.stefanini.taskmanager.domain.User;


public class HibernateSessionManager {
	private static SessionFactory sessionFactory;
	private static final Logger logger = LogManager.getLogger(HibernateSessionManager.class);


    public HibernateSessionManager() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Task.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
            	logger.error("SessionFactory creation failed" +  e);
            }
        }
		return sessionFactory;
    }
  }

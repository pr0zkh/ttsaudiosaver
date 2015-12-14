package org.ttsaudiosaver.web.model.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO {
	
	private static final Logger logger = Logger.getLogger(AbstractDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		logger.info("Inside getSession method: session factory - " + sessionFactory);
		return sessionFactory.getCurrentSession();
	}
	
	public void persist(Object entity) {
		getSession().persist(entity);
	}
	
	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
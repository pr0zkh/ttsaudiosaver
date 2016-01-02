package org.ttsaudiosaver.web.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class AbstractDAO extends HibernateDaoSupport {
	
	@Autowired
	private void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
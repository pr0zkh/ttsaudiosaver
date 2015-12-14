package org.ttsaudiosaver.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.user.UserDAO;

@Service
public class RegistrationService {
	
	@Autowired
	@Qualifier(value = "userDao")
	private UserDAO userDAO;
	
	public User register(User user) {
		userDAO.saveUser(user);
		return user;
	}

}

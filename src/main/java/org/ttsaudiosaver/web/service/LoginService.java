package org.ttsaudiosaver.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.user.UserDAO;

@Service
public class LoginService {
	
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDAO;
	
	public User login(String email, String password) throws UserNotFoundException, LoginFailedException {
		User user = userDAO.findUserByEmail(email);
		if(user != null) {
			if(password.equals(user.getPassword())) {
				return user;
			} else {
				throw new LoginFailedException("Given password for user with email " + email + " is incorrect");
			}
		} else {
			throw new UserNotFoundException("Could not find user with email " + email);
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public class UserNotFoundException extends Exception {
		private static final long serialVersionUID = 3697399599142192081L;
		
		public UserNotFoundException() {
			super();
		}
		
		public UserNotFoundException(String message) {
			super(message);
		}
		
		public UserNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	public class LoginFailedException extends Exception {
		private static final long serialVersionUID = 4262497436813847732L;
		
		public LoginFailedException() {
			super();
		}
		
		public LoginFailedException(String message) {
			super(message);
		}
		
		public LoginFailedException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}

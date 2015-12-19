package org.ttsaudiosaver.web.service.profile;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.user.UserDAO;
import org.ttsaudiosaver.web.service.email.EmailService;
import org.ttsaudiosaver.web.service.email.SimpleEmailMessage;

@Service
public class ProfileService {

	public static final String PWD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
	public static final Integer PWD_GEN_LENGTH = 15;

	@Autowired
	@Qualifier("userDao")
	private UserDAO userDAO;

	@Autowired
	private EmailService emailService;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostConstruct
	private void init() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public User login(String email, String password) throws UserNotFoundException, LoginFailedException {
		User user = userDAO.findUserByEmail(email);
		if (user != null) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return user;
			} else {
				throw new LoginFailedException("Given password for user with email " + email + " is incorrect");
			}
		} else {
			throw new UserNotFoundException("Could not find user with email " + email);
		}
	}

	public User fbLogin(String email, String username, String profilePicUrl) {
		User user = userDAO.findUserByEmail(email);
		if (user != null) {
			return user;
		} else {
			user = new User();
			String generatedPassword = generatePassword();
			user.setEmail(email);
			user.setProfilePicUrl(profilePicUrl);
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			userDAO.saveUser(user);
			sendFbRegistrationEmail(username, generatedPassword, email);
		}
		return user;
	}
	
	public User register(String username, String password, String email) throws UserAlreadyExistsException {
		User user = userDAO.findUserByEmail(email);
		if(user != null) {
			throw new UserAlreadyExistsException("User with email " + email + " already exists.");
		} else {
			user = new User();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email);
			userDAO.saveUser(user);
		}
		return user;
	}
	
	public User changePassword(User user, String oldPassword, String newPassword) throws UserUpdateFailedException {
		if(passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userDAO.updateUser(user);
			sendChangePasswordEmail(user.getUsername(), user.getEmail());
			return user;
		} else {
			throw new UserUpdateFailedException("User's old password doesn't match to that one that was provided.");
		}
	}

	private String generatePassword() {
		return RandomStringUtils.random(PWD_GEN_LENGTH, PWD_CHARS);
	}

	private void sendFbRegistrationEmail(String username, String password, String email) {
		SimpleEmailMessage msg = new SimpleEmailMessage();
		msg.setContent(
				"Hi " + username + "!\n\nThank you for using our service. We generated password for you, here is it: "
						+ password + "\n\nCheers!");
		msg.setSubject("Registration details");
		msg.setRecipient(email);
		emailService.sendMessage(msg);
	}
	
	private void sendChangePasswordEmail(String username, String email) {
		SimpleEmailMessage msg = new SimpleEmailMessage();
		msg.setContent(
				"Hi " + username + "!\n\nYou have recently changed your password.\nIf you'are not aware of this activity, please, contact our technical support as soon as possible.\n\nCheers!");
		msg.setSubject("Password change");
		msg.setRecipient(email);
		emailService.sendMessage(msg);
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
	
	public class UserAlreadyExistsException extends Exception {

		private static final long serialVersionUID = -333087333444828930L;

		public UserAlreadyExistsException() {
			super();
		}
		
		public UserAlreadyExistsException(String message) {
			super(message);
		}
		
		public UserAlreadyExistsException(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
	public class UserUpdateFailedException extends Exception {

		private static final long serialVersionUID = -333087333444828930L;

		public UserUpdateFailedException() {
			super();
		}
		
		public UserUpdateFailedException(String message) {
			super(message);
		}
		
		public UserUpdateFailedException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}

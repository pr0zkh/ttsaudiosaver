package org.ttsaudiosaver.web.service.profile;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ttsaudiosaver.web.model.CompiledAudio;
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
		User fbUser = userDAO.findUserByFbEmail(email);
		if (user != null) {
			return user;
		} else if(fbUser != null) {
			return fbUser;
		} else {
			user = new User();
			String generatedPassword = generatePassword();
			user.setEmail(email);
			user.setFbEmail(email);
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
	
	public void restorePassword(String email) throws UserNotFoundException {
		User user = userDAO.findUserByEmail(email);
		if(user == null) {
			throw new UserNotFoundException("Cannot find user with given email");
		} else {
			String generatedPassword = generatePassword();
			user.setPassword(passwordEncoder.encode(generatedPassword));
			userDAO.updateUser(user);
			sendRestorePasswordEmail(user.getUsername(), generatedPassword, user.getEmail());
		}
	}

	public User updateProfileInfo(User currentUser, String newUsername, String newEmail) throws UserAlreadyExistsException {
		User user = userDAO.findUserByEmail(newEmail);
		if(user != null) {
			throw new UserAlreadyExistsException("Sorry, this email address is already in use");
		} else {
			String oldEmail = currentUser.getEmail();
			currentUser.setEmail(newEmail);
			currentUser.setUsername(newUsername);
			userDAO.updateUser(currentUser);
			if(!oldEmail.equals(currentUser.getEmail())) {
				sendUpdateProfileDetailsEmail(currentUser.getUsername(), oldEmail, currentUser.getEmail());
			}
		}
		return currentUser;
	}
	
	public User addAudio(User currentUser, CompiledAudio audio) {
		currentUser.addCompiledAudio(audio);
		userDAO.updateUser(currentUser);
		return currentUser;
	}

	private String generatePassword() {
		return RandomStringUtils.random(PWD_GEN_LENGTH, PWD_CHARS);
	}

	private void sendFbRegistrationEmail(String username, String password, String email) {
		SimpleEmailMessage msg = new SimpleEmailMessage();
		String greeting = StringUtils.isNotBlank(username) ? "Hi " + username + "!" : "Hi!";
		msg.setContent(greeting + "\n\nThank you for using our service. We generated password for you, here's it: "
						+ password + "\nYou can change it on profile details page.\n\nCheers!");
		msg.setSubject("Registration details");
		msg.setRecipient(email);
		emailService.sendMessage(msg);
	}
	
	private void sendChangePasswordEmail(String username, String email) {
		String greeting = StringUtils.isNotBlank(username) ? "Hi " + username + "!" : "Hi!";
		SimpleEmailMessage msg = new SimpleEmailMessage();
		msg.setContent(greeting + "\n\nYou have recently changed your password.\n"
				+ "If you'are not aware of this activity, please, contact our technical support as soon as possible."
				+ "\n\nCheers!");
		msg.setSubject("Password change");
		msg.setRecipient(email);
		emailService.sendMessage(msg);
	}
	
	private void sendRestorePasswordEmail(String username, String password, String email) {
		SimpleEmailMessage msg = new SimpleEmailMessage();
		String greeting = StringUtils.isNotBlank(username) ? "Hi " + username + "!" : "Hi!";
		msg.setContent(greeting + "\n\nHere's your new password: " + password + "\n"
						+ "You can change it on profile details page.\n\nCheers!");
		msg.setSubject("Forgot password?");
		msg.setRecipient(email);
		emailService.sendMessage(msg);
	}
	
	private void sendUpdateProfileDetailsEmail(String username, String oldEmail, String newEmail) {
		SimpleEmailMessage toOld = new SimpleEmailMessage();
		SimpleEmailMessage toNew = new SimpleEmailMessage();
		String greeting = StringUtils.isNotBlank(username) ? "Hi " + username + "!" : "Hi!";
		
		toOld.setContent(greeting + "\n\nYou've recently changed your email address.\n"
				+ "Now it's " + newEmail + ". If you're not aware of this activity, please, contact our technical support"
						+ " as soon as possible.\n\nCheers!");
		toOld.setSubject("Email address has been changed");
		toOld.setRecipient(oldEmail);
		
		toNew.setContent(greeting + "\n\nYou've successfully changed your email address.\n"
				+ "Just in case - your old one was " + oldEmail + "\n\nCheers!");
		toNew.setSubject("Email address has been changed");
		toNew.setRecipient(newEmail);
		
		emailService.sendMessages(Arrays.asList(toOld, toNew));
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

package org.ttsaudiosaver.web.model.dao.user;

import java.util.List;

import org.ttsaudiosaver.web.model.User;

public interface UserDAO {
	
	void saveUser(User user);
	List<User> findAllUsers();
	void deleteUserById(String userId);
	User findUserById(String userId);
	void updateUser(User user);
}
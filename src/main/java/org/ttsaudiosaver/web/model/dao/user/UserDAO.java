package org.ttsaudiosaver.web.model.dao.user;

import org.ttsaudiosaver.web.model.User;

public interface UserDAO {
	
	void saveUser(User user);
	User findUserByEmail(String email);
	User findUserByFbEmail(String email);
	void updateUser(User user);
}
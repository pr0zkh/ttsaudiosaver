package org.ttsaudiosaver.web.model.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("userDao")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserDAOImpl extends AbstractDAO implements UserDAO {

	@Override
	public void saveUser(User user) {
		getHibernateTemplate().persist(user);
	}
	
	@Override
	public User findUserByEmail(String email) {
		User user = null;
		List<?> users = getHibernateTemplate().findByNamedQueryAndNamedParam(User.Constants.QUERY_FIND_BY_EMAIL, User.Constants.PARAM_EMAIL, email);
		if(!users.isEmpty()) {
			user = (User) users.get(0);
			getHibernateTemplate().initialize(user.getCompiledAudios());
			for(CompiledAudio audio: user.getCompiledAudios()) {
				getHibernateTemplate().initialize(audio.getPairsIncluded());
			}
		}
		return user;
	}
	
	@Override
	public User findUserByFbEmail(String fbEmail) {
		User user = null;
		List<?> users = getHibernateTemplate().findByNamedQueryAndNamedParam(User.Constants.QUERY_FIND_BY_FB_EMAIL, User.Constants.PARAM_FB_EMAIL, fbEmail);
		if(!users.isEmpty()) {
			user = (User) users.get(0);
			getHibernateTemplate().initialize(user.getCompiledAudios());
			for(CompiledAudio audio: user.getCompiledAudios()) {
				getHibernateTemplate().initialize(audio.getPairsIncluded());
			}
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}
}
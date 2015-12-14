package org.ttsaudiosaver.web.model.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("userDao")
public class UserDAOImpl extends AbstractDAO implements UserDAO {
	
	private static final String DELETE_BY_ID_QUERY = "delete from users where user_id = :user_id";

	@Override
	@Transactional
	public void saveUser(User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> findAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	@Override
	@Transactional
	public void deleteUserById(String userId) {
		Query query = getSession().createSQLQuery(DELETE_BY_ID_QUERY);
		query.setString("user_id", userId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public User findUserById(String userId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("user_id", userId));
		return (User) criteria.uniqueResult();
	}
	
	@Override
	@Transactional
	public User findUserByEmail(String email) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		getSession().update(user);
	}
}
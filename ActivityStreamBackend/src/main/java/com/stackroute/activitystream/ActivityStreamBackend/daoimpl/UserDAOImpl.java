package com.stackroute.activitystream.ActivityStreamBackend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.ActivityStreamBackend.Model.UserCredentials;
import com.stackroute.activitystream.ActivityStreamBackend.Model.UserModel;
import com.stackroute.activitystream.ActivityStreamBackend.dao.UserDAO;

@Repository(value = "userDAO")
@Transactional
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	public UserModel findByEmail(String email) {
		UserModel userModel = new UserModel();
		userModel = (UserModel) sessionFactory.getCurrentSession().get(UserModel.class, email);
		return userModel;
	}

	public boolean saveUser(UserModel user) {
		try {
			UserCredentials userCredentials = new UserCredentials();
			userCredentials.setEmail(user.getEmail());
			userCredentials.setPassword(user.getFName());
			sessionFactory.getCurrentSession().save(user);
			sessionFactory.getCurrentSession().save(userCredentials);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateUser(UserModel user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUserByEmail(UserModel user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<UserModel> findAllUsers() {
		List<UserModel> showuser = sessionFactory.getCurrentSession().createQuery("FROM UserModel").list();
		return showuser;
	}





}

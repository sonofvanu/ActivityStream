package com.stackroute.activitystream.ActivityStreamBackend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.activitystream.ActivityStreamBackend.Model.UserModel;

@Service
public interface UserDAO {

	public UserModel findByEmail(String email);

	public boolean saveUser(UserModel user);

	public boolean updateUser(UserModel user);

	public boolean deleteUserByEmail(UserModel user);

	public List<UserModel> findAllUsers();

	

}

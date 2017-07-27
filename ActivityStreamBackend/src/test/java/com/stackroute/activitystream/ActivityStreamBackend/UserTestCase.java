package com.stackroute.activitystream.ActivityStreamBackend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.activitystream.ActivityStreamBackend.Model.UserModel;
import com.stackroute.activitystream.ActivityStreamBackend.dao.UserDAO;

public class UserTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static UserDAO userDAO;
	@Autowired
	private static UserModel userModel;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.stackroute.activitystream.ActivityStreamBackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		userModel = (UserModel) context.getBean("userModel");
	}
	
	@Test
	public void testSaveUser() {
		
		UserModel userModel = (UserModel) context.getBean("userModel");
		userModel.setEmail("omg@gmail.com");
		userModel.setFName("omghelloworld");
		userModel.setPhno(1234567890);
		userModel.setUsername("omghworld");
		userModel.setAddress("omgbangalore");
		userModel.setPassword("omgyuck");
		assertEquals(true, userDAO.saveUser(userModel));
	}
	
	@Test
	public void testGetUserByEmail() {
		
		//UserModel userModel = new UserModel();
		UserModel userByName = userDAO.findByEmail("immad@gmail.com");
		assertNotNull(userByName);
		System.out.println("User Email ID :" + userByName.getFName());
	}
	
	@Test
	public void testDeleteUser() {
		userModel = userDAO.findByEmail("hello@gmail.com");
		boolean userToDelete = userDAO.deleteUserByEmail(userModel);
		assertTrue(userToDelete);
	}
	
	@Test
	public void updateUser() {
		
		//UserModel userModel = new UserModel();
		userModel = userDAO.findByEmail("immad@gmail.com");
		System.out.println(userModel.getFName());
		userModel.setAddress("Bangalore city");
		boolean updatingUser = userDAO.updateUser(userModel);
		assertEquals(true, updatingUser);
	}

	
	@Test
	public void testForGetAllUsers() {
		
		List<UserModel> userList = userDAO.findAllUsers();
		assertNotNull(userList);
		for (UserModel userDataBasaData : userList) {
			System.out.println(userDataBasaData.getUsername());
		}
	}

}

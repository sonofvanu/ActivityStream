package com.stackroute.activitystream.ActivityStreamRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.ActivityStreamBackend.Model.UserModel;
import com.stackroute.activitystream.ActivityStreamBackend.dao.UserDAO;

@RestController
public class UserController {

	@Autowired
	UserDAO userdao; // Service which will do all data retrieval/manipulation
						// work

	// -------------------Retrieve All
	// Users--------------------------------------------------------
	// @RequestMapping(value = "/user", method = RequestMethod.GET)
	@GetMapping(value = "/user")
	public ResponseEntity<List<UserModel>> listAllUsers() {
		List<UserModel> users = userdao.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserModel>>(HttpStatus.NO_CONTENT);} else {
			return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
		}
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------
	// @RequestMapping(value = "/user/{email}", method = RequestMethod.GET,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> getUser(@PathVariable("email") String email) {
		System.out.println("Fetching User with name " + email);
		UserModel user = userdao.findByEmail(email);
		if (user == null) {
			System.out.println("User with name " + email + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserModel>(user, HttpStatus.OK);
	}

	// -------------------Create a
	// User--------------------------------------------------------
	// @RequestMapping(value = "/user/", method = RequestMethod.POST)
	@PostMapping(value = "/user/")
	public ResponseEntity<String> createUser(@RequestBody UserModel user) {
		System.out.println("Creating User " + user.getUsername());
		if (userdao.findByEmail(user.getEmail()) == null) {
			userdao.saveUser(user);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------- Update a User
	// --------------------------------------------------------
	// @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
	@PutMapping(value = "/user/{email}")
	public ResponseEntity<UserModel> updateUser(@PathVariable("email") String email, @RequestBody UserModel user) {
		System.out.println("Fetching & updating User with email " + email);
		UserModel currentuser = userdao.findByEmail(email);
		if (currentuser == null) {
			System.out.println("Unable to update. User with email " + email + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		} else {
			currentuser.setFName(user.getFName());
			currentuser.setUsername(user.getUsername());
			currentuser.setPhno(user.getPhno());
			currentuser.setAddress(user.getAddress());
			currentuser.setPassword(user.getPassword());
			userdao.updateUser(currentuser);
			return new ResponseEntity<UserModel>(HttpStatus.OK);
		}
	}

	// ------------------- Delete a User
	// --------------------------------------------------------
	// @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/user/{email}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable("email") String email) {
		System.out.println("Fetching & Deleting User with email " + email);
		UserModel user = userdao.findByEmail(email);
		if (user == null) {
			System.out.println("Unable to delete. User with name " + email + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}

		userdao.deleteUserByEmail(user);
		return new ResponseEntity<UserModel>(HttpStatus.OK);
	}

}

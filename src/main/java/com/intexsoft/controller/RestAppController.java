package com.intexsoft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.intexsoft.model.User;
import com.intexsoft.services.UserService;

/**
 * 
 */
@RestController
public class RestAppController
{
	static final Logger logger = LoggerFactory.getLogger(RestAppController.class);
	@Autowired
	UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers()
	{
		logger.info("Retrieve All Users");
		List<User> users = userService.findAllUsers();
		if (users.isEmpty())
		{
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id)
	{
		logger.info("Fetching User with id: {}", id);
		User user = userService.findById(id);
		if (user == null)
		{
			logger.info("User with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
	{
		logger.info("Creating User: {}", user.userName);
		if (userService.isUserExist(user))
		{
			logger.info("A User with name {} already exist", user.userName);
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.id).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user)
	{
		logger.info("Updating User with id: {}", id);
		User currentUser = userService.findById(id);
		if (currentUser == null)
		{
			logger.info("User with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		currentUser.userName = user.userName;
		currentUser.address = user.address;
		currentUser.email = user.email;
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id)
	{
		logger.info("Fetching & Deleting User with id: ", id);
		User user = userService.findById(id);
		if (user == null)
		{
			logger.info("Unable to delete. User with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers()
	{
		logger.info("Deleting All Users");
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}

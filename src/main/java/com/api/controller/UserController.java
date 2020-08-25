package com.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.api.entity.User;
import com.api.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUser() {
		List<User> listUser = userService.findAllUser();
		return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getProductById(@PathVariable("user_id") Integer userId) {
		Optional<User> user = userService.findById(userId);

		if (!user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		// Set url
		headers.setLocation(builder.path("/users/{userId}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("user_id") Integer userId,
			@RequestBody User user) {
		Optional<User> currentUser = userService.findById(userId);

		if (!currentUser.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		currentUser.get().setUserId(user.getUserId());
		currentUser.get().setUserName(user.getUserName());
		currentUser.get().setPassword(user.getPassword());
		currentUser.get().setRole(user.getRole());
		
		userService.save(currentUser.get());
		return new ResponseEntity<User>(currentUser.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("user_id") Integer userId) {
		Optional<User> user = userService.findById(userId);
		if (!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.remove(user.get());
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}

package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.LoginRequest;
import com.user.dto.LoginResponse;
import com.user.pojo.User;
import com.user.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/user/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {

		logger.info("addUser method from User controller is accessed");

		User newUser = userService.saveUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(newUser, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/user/all")
	public List<User> fetchAllUser() {

		logger.info("fetchAllUser method from User controller is accessed");

		List<User> allUser = userService.getAllUser();
		return allUser;
	}

	@GetMapping("/user/find/{userId}")
	public ResponseEntity<User> fetchUserById(@PathVariable("userId") int userId) {

		logger.info("UserById method from User controller is accessed");

		ResponseEntity<User> reponseEntity = null;
		User user = userService.getUserById(userId);
		reponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return reponseEntity;
	}

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {

		logger.info("signIn method from Admin controller is accessed");

		User user = userService.doLogin(loginRequest.getUserName(), loginRequest.getPassword(), loginRequest.getRole());

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUserId(user.getUserId());
		loginResponse.setUserName(user.getUserName());
		loginResponse.setRole(user.getRole());
		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		logger.info("updateAdmin method from Admin controller is accessed");

		User updatedUser = userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/user/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") int userId) {

		logger.info("deleteUser method from User controller is accessed");

		userService.deleteUserById(userId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/user/bylocation/{location}")
	public List<User> fetchUserbyLocation(@PathVariable("location") String location) {

		logger.info("UserBylocation method from User controller is accessed");

		List<User> userByLocation = userService.getAllUserByLocation(location);
		return userByLocation;
	}

	@GetMapping("/user/{userName}")
	public User fetchUserbyUserName(@PathVariable("userName") String userName) {

		logger.info("UserByUsername method from User controller is accessed");

		User userByUserName = userService.getAllUserByUserName(userName);
		return userByUserName;
	}

	@GetMapping("/user/byphone/{phone}")
	public Optional<User> fetchUserbyPhone(@PathVariable("phone") long phone) {

		logger.info("UserByPhone method from User controller is accessed");

		Optional<User> userByPhone = userService.getAllUserByPhone(phone);
		return userByPhone;
	}

}

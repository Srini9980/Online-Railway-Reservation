package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.exception.UserNotFoundException;
import com.user.pojo.User;
import com.user.repository.UserRepository;

import nonapi.io.github.classgraph.utils.Assert;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserRepository userRepository;

	@Test
	public void testGetUserById() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(10)).thenReturn(optionalUser);
		User myUser = userService.getUserById(10);
		assertEquals("srinivas", myUser.getUserName());

	}

	@Test
	public void testGetUserByIdWithException() {

		when(userRepository.findById(11)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.getUserById(11));
	}

	@Test
	public void testGetAllUser() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		User user1 = new User();
		user1.setUserId(10);
		user1.setFirstName("srinivas");
		user1.setLastName("v");
		user1.setAge(24);
		user.setGender("male");
		user1.setPhone(454545);
		user1.setLocation("bangalore");
		user1.setUserName("srinivas");
		user1.setEmail("srinivas@mail.com");
		user1.setPassword("454545");

		User user2 = new User();
		user2.setUserId(10);
		user2.setFirstName("srinivas");
		user2.setLastName("v");
		user2.setAge(24);
		user2.setGender("male");
		user2.setPhone(454545);
		user2.setLocation("bangalore");
		user2.setUserName("srinivas");
		user2.setEmail("srinivas@mail.com");
		user2.setPassword("454545");

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);

		when(userRepository.findAll()).thenReturn(userList);
		List<User> users = userService.getAllUser();
		assertEquals(3, users.size());
	}

	@Test
	public void testUpdateUser() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(10)).thenReturn(optionalUser);
		userService.updateUser(user);
		verify(userRepository, times(1)).findById(10);
		verify(userRepository, times(1)).save(user);

	}

	@Test
	public void testUpdateUserWithException() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		when(userRepository.findById(11)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));

	}

	@Test
	public void testDeleteUserById() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(10)).thenReturn(optionalUser);
		userService.deleteUserById(10);
		verify(userRepository, times(1)).findById(10);
		verify(userRepository, times(1)).deleteById(10);

	}

	@Test
	public void testDeleteUserByIdWithException() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		when(userRepository.findById(11)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(11));

	}

	@Test
	public void testGetAllUserByUserName() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("praveen");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		when(userRepository.findByUserName("srinivas")).thenReturn(user);
		userService.getAllUserByUserName("srinivas");
		assertEquals("praveen", user.getUserName());

	}

	@Test
	public void testGetAllUserByUserNameWithExcepton() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		when(userRepository.findByUserName("sriniva")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.getAllUserByUserName("sriniva"));

	}

	@Test
	public void testGetAllUserByPhone() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findByPhone(454545)).thenReturn(optionalUser);
		userService.getAllUserByPhone(454545);
		verify(userRepository, times(1)).findByPhone(454545);

	}
	
	@Test
	public void testGetAllUserByPhoneWithException() {
		
		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");
		
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findByPhone(12345)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.getAllUserByPhone(12345));
		
	}

	@Test
	public void testGetAllUserByLocation() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		User user1 = new User();
		user1.setUserId(11);
		user1.setFirstName("shahsi");
		user1.setLastName("v");
		user1.setAge(26);
		user.setGender("female");
		user1.setPhone(12345);
		user1.setLocation("chennai");
		user1.setUserName("shahsi");
		user1.setEmail("shahsi@mail.com");
		user1.setPassword("12345");

		User user2 = new User();
		user2.setUserId(12);
		user2.setFirstName("kavi");
		user2.setLastName("v");
		user2.setAge(45);
		user2.setGender("female");
		user2.setPhone(987654);
		user2.setLocation("hyderabad");
		user2.setUserName("kavi");
		user2.setEmail("kavi@mail.com");
		user2.setPassword("987654");

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);

		when(userRepository.findByLocation("bangalore")).thenReturn(userList);
		List<User> userByLocation = userService.getAllUserByLocation("bangalore");
		assertEquals(3, userByLocation.size());

	}

	@Test
	public void testGetALlUserByLocationWithException() {

		User user = new User();
		user.setUserId(10);
		user.setFirstName("srinivas");
		user.setLastName("v");
		user.setAge(24);
		user.setGender("male");
		user.setPhone(454545);
		user.setLocation("bangalore");
		user.setUserName("srinivas");
		user.setEmail("srinivas@mail.com");
		user.setPassword("454545");

		when(userRepository.findByLocation("chennai")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userService.getAllUserByLocation("chennai"));

	}

}

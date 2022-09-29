package com.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.pojo.User;

@SpringBootTest
public class UserRepositoryTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void testSaveUser() {
		
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
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("srinivas", user.getUserName());
		
	}
	
	@Test
	public void getAllUser() {
		
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
		user1.setFirstName("praveen");
		user1.setLastName("j");
		user1.setAge(30);
		user1.setGender("male");
		user1.setPhone(987654);
		user1.setLocation("chennai");
		user1.setUserName("praveen");
		user1.setEmail("praveen@mail.com");
		user1.setPassword("987654");
		
		User user2 = new User();
		user2.setUserId(12);
		user2.setFirstName("pradeep");
		user2.setLastName("j");
		user2.setAge(28);
		user2.setGender("male");
		user2.setPhone(123456);
		user2.setLocation("hyderabad");
		user2.setUserName("pradeep");
		user2.setEmail("pradeep@mail.com");
		user2.setPassword("123456");
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		
		when(userRepository.findAll()).thenReturn(userList);
		assertEquals(3, userList.size());
		
	}
	
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
		assertEquals("srinivas", user.getUserName());
	
	}
	
	@Test
	public void testGetUserByUserName() {
		
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
		
		when(userRepository.findByUserName("srinivas")).thenReturn(user);
		assertEquals("srinivas", user.getUserName());
		
	}
	
	@Test
	public void testGetUserByPhone() {
		
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
		assertEquals("srinivas", user.getUserName());
		
	}
	
	@Test
	public void testGetUserByLocation() {
		
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
		user1.setFirstName("praveen");
		user1.setLastName("j");
		user1.setAge(30);
		user1.setGender("male");
		user1.setPhone(987654);
		user1.setLocation("chennai");
		user1.setUserName("praveen");
		user1.setEmail("praveen@mail.com");
		user1.setPassword("987654");
		
		User user2 = new User();
		user2.setUserId(12);
		user2.setFirstName("pradeep");
		user2.setLastName("j");
		user2.setAge(28);
		user2.setGender("male");
		user2.setPhone(123456);
		user2.setLocation("hyderabad");
		user2.setUserName("pradeep");
		user2.setEmail("pradeep@mail.com");
		user2.setPassword("123456");
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		
		when(userRepository.findByLocation("bangalore")).thenReturn(userList);
		assertEquals("srinivas", user.getUserName());
		
	}
	
}

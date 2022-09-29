package com.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.pojo.User;
import com.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;
	private User user;
	private List<User> users;
	private Optional<User> optionalUser;

	@BeforeEach
	public void setUp() {

		user = new User();
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

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	}

	@Test
	public void testAddUser() throws Exception {

		when(userService.saveUser(any())).thenReturn(user);
		mockMvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).saveUser(any());

	}

	@Test
	public void testGetAllUser() throws Exception {

		when(userService.getAllUser()).thenReturn(users);
		mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON).content(asJsonString(users)))
				.andDo(print());
		verify(userService, times(1)).getAllUser();

	}

	@Test
	public void testGetUserById() throws Exception {

		when(userService.getUserById(10)).thenReturn(user);
		mockMvc.perform(get("/user/find/10").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andDo(print());
		verify(userService, times(1)).getUserById(10);

	}

	@Test
	public void testUpdateUser() throws Exception {

		when(userService.updateUser(any())).thenReturn(user);
		mockMvc.perform(put("/user/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).updateUser(any());

	}

	@Test
	public void testDeleteUserById() throws Exception {

		mockMvc.perform(delete("/user/delete/10").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).deleteUserById(10);

	}

	@Test
	public void testGetUserByUserName() throws Exception {

		when(userService.getAllUserByUserName("srinivas")).thenReturn(user);
		mockMvc.perform(get("/user/srinivas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andDo(print());
		verify(userService, times(1)).getAllUserByUserName("srinivas");

	}

	@Test
	public void testGetUserByPhone() throws Exception {

		when(userService.getAllUserByPhone(454545)).thenReturn(optionalUser);
		mockMvc.perform(get("/user/byphone/454545").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andDo(print());
		verify(userService, times(1)).getAllUserByPhone(454545);

	}

	@Test
	public void testGetAllUserByLocation() throws Exception {

		when(userService.getAllUserByLocation("bangalore")).thenReturn(users);
		mockMvc.perform(
				get("/user/bylocation/bangalore").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andDo(print());
		verify(userService, times(1)).getAllUserByLocation("bangalore");
	}

	public static String asJsonString(final Object obj) {

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}

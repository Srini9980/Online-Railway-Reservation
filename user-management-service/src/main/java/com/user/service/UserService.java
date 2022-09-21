package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.pojo.User;

public interface UserService {
	
	public User doLogin(String userName, String password, String role);

	public User saveUser(User user);

	public List<User> getAllUser();

	public User getUserById(int userId);

	public User updateUser(User user);

	public void deleteUserById(int userId);
	
	public User getAllUserByUserName(String userName);
	
	public Optional<User> getAllUserByPhone(long phone);
	
	public List<User> getAllUserByLocation(String location);
	
	public int getSequenceNumber(String sequenceName);

}

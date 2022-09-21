package com.user.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.user.exception.AuthenticationFailedException;
import com.user.exception.PhoneNumberAlreadyExistingException;
import com.user.exception.UserNameAlreadyExistingException;
import com.user.exception.UserNotFoundException;
import com.user.pojo.DatabaseSequence;
import com.user.pojo.User;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public User doLogin(String userName, String password, String role) {

		User user = userRepository.login(userName, password, role);
		if (user == null) {
			throw new AuthenticationFailedException("Username or password are invalid");
		}
		return user;
	}

	@Override
	public User saveUser(User user) {

//		To make the userName unique, we declared the exception on line 37 to 40

		Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserName(user.getUserName()));

		if (optionalUser.isPresent()) {
			throw new UserNameAlreadyExistingException("Username already exists with " +"->"+ user.getUserName());
		}

		Optional<User> userByPhone = userRepository.findByPhone(user.getPhone());
		if (userByPhone.isPresent()) {
			throw new PhoneNumberAlreadyExistingException("Phone number already exists " + user.getPhone());
		}
		user.setUserId(getSequenceNumber(User.SEQUENCE_NAME));
		User newUser = userRepository.save(user);
		return newUser;

	}

	@Override
	public List<User> getAllUser() {

		List<User> allUser = userRepository.findAll();
		return allUser;
	}

	@Override
	public User getUserById(int userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + userId);
		}
		User userById = optionalUser.get();
		return userById;

	}

	@Override
	public User updateUser(User user) {

		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + user.getUserId());
		}
		User updatedUser = userRepository.save(user);
		return updatedUser;

	}

	@Override
	public void deleteUserById(int userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("No user found with this id :" + userId);
		}

		userRepository.deleteById(userId);

	}

	@Override
	public User getAllUserByUserName(String userName) {

		User userByName = userRepository.findByUserName(userName);
		if (userByName.getUserName() == userName) {
			throw new UserNameAlreadyExistingException("Username already exists");
		}
		return userByName;
	}

	@Override
	public Optional<User> getAllUserByPhone(long phone) {

		Optional<User> userByPhone = userRepository.findByPhone(phone);
		if (userByPhone.isEmpty()) {
			throw new UserNotFoundException("No user found with this phone number : " + phone);
		}
		return userByPhone;
	}

	@Override
	public List<User> getAllUserByLocation(String location) {

		List<User> userByLocation = userRepository.findByLocation(location);
		if (userByLocation.isEmpty()) {
			throw new UserNotFoundException("No user found with this location : " + location);
		}
		return userByLocation;

	}

	@Override
	public int getSequenceNumber(String sequenceName) {

		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seq", 1);
		DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}

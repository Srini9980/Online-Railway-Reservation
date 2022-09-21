package com.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.user.pojo.User;

public interface UserRepository  extends MongoRepository<User, Integer>{
	    
    @Query("{'phone':?0 }")
    public Optional<User> findByPhone(long phone);
    
    @Query("{'location':?0 }")
    public List<User> findByLocation(String location);
    
    @Query("{'userName' : ?0 }")
    public User findByUserName(String userName);
	
    @Query("{'userName':?0, 'password':?1, 'role':?2 }")
	public User login(String userName, String password, String role);

}

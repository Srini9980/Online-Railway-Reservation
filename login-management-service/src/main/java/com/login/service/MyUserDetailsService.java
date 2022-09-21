package com.login.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.login.dto.LoginResponse;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	 @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        
	    	LoginResponse loginResponse=restTemplate.getForObject("http://user-management-service/user/" + userName, LoginResponse.class);
	    	GrantedAuthority auth=new SimpleGrantedAuthority(loginResponse.getRole());
	    	ArrayList<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
	    	list.add(auth);
	    	UserDetails userDetails=new User(loginResponse.getUserName(),
	    			loginResponse.getPassword(), list);
	    	return userDetails;
		 
//		 User user = restTemplate.getForObject("http://localhost:8081/user/"+userName, User.class);
//		 return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
	    	
	    }

}
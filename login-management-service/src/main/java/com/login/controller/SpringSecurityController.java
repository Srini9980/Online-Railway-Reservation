package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.login.dto.AuthenticationRequest;
import com.login.dto.AuthenticationResponse;
import com.login.dto.LoginResponse;
import com.login.service.MyUserDetailsService;
import com.login.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SpringSecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		LoginResponse loginResponse = restTemplate.getForObject("http://user-management-service/user/"+authenticationRequest.getUserName(), LoginResponse.class);
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		authenticationResponse.setUserId(loginResponse.getUserId());
		authenticationResponse.setUserRole(loginResponse.getRole());
		authenticationResponse.setJwt(jwt);
		return ResponseEntity.ok(authenticationResponse);

	}

}

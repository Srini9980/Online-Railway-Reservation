package com.login.dto;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	private String userName;
	private String password;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	// need default constructor for JSON Parsing
	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String username, String password,String role) {
		this.setUserName(username);
		this.setPassword(password);
	}
}

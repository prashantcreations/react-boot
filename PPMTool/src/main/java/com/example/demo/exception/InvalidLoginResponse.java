package com.example.demo.exception;

public class InvalidLoginResponse {
	
	String username;
	String password;
	
	public InvalidLoginResponse() {
		this.username = "Invalid-User";
		this.password = "Invalid-Password";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

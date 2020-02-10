package com.example.demo.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message = "Username cant not blank")
	private String userName;
	@NotBlank(message = "Password cant not blank")
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
}



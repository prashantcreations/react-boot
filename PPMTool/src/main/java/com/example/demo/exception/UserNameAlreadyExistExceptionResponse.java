package com.example.demo.exception;

public class UserNameAlreadyExistExceptionResponse {
	
	private String userName;
	
	public UserNameAlreadyExistExceptionResponse(String userName){
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

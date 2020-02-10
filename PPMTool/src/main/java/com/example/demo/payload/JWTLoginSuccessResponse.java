package com.example.demo.payload;

public class JWTLoginSuccessResponse {
	private boolean success;
	private String token;
	
	
	public JWTLoginSuccessResponse(boolean success, String response) {
		super();
		this.success = success;
		this.token = response;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String response) {
		this.token = response;
	}
	@Override
	public String toString() {
		return "JWTLoginSuccessResponse [success=" + success + ", response=" + token + ", isSuccess()=" + isSuccess()
				+ ", getResponse()=" + getToken() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}

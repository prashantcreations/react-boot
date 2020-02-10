package com.example.demo.security;

public class SecurityContant {
	
	public static final String SIGN_UP_URL="/api/user/**";
	public static final String H2_CONSOLE_URL="h2-console/**";
	public static final String SECRET="SecretKeyToGenJWTs";
	public static final String TOKEN_PREFIX = "Bearer ";//it must suffix space
	public static final String HEADER_STRING = "Authorization";//it must suffix space
	public static final long EXPIRATION_TIME = 30_000;  //30 SEC //it must suffix space
}

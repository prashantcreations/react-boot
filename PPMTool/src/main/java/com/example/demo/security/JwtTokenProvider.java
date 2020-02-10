package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	// Generate the token
	
	public String generateTheToken(Authentication authentication){
		User user = (User)authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(now.getTime()+com.example.demo.security.SecurityContant.EXPIRATION_TIME);
		
		String userId = Long.toString(user.getId());
		
		Map<String,Object> claims = new HashMap<>();// key must be same as pojo user
		claims.put("id",userId);
		claims.put("userName",user.getUsername());
		claims.put("fullName",user.getFullName());
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, com.example.demo.security.SecurityContant.SECRET)
				.compact();
	}
	
	//Validate the token
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(com.example.demo.security.SecurityContant.SECRET).parseClaimsJws(token);
			return true;
		}catch(SignatureException ex) {
			System.out.println("Invalid JWT singnature");
		}catch(MalformedJwtException mf) {
			System.out.println("Invalid JWT Token exception");
		}catch(ExpiredJwtException ej) {
			System.out.println("JWT token has expired");
		}catch (UnsupportedJwtException ue) {
			System.out.println("JWT unsupported exception");
		}catch(IllegalArgumentException iae) {
			System.out.println("JWT Claims is empty string");
		}
		return false;
	}
	
	// get userid from token 
	
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(com.example.demo.security.SecurityContant.SECRET).parseClaimsJws(token).getBody();
		String id = (String)claims.get("id");	
		return Long.parseLong(id);
	}

}

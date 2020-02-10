package com.example.demo.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.domain.User;
import com.example.demo.services.CustomUserDetailService;

import antlr.StringUtils;
import io.jsonwebtoken.lang.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	CustomUserDetailService customerUserDetailService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt =getJwt(request);
			boolean isEmpty= org.springframework.util.StringUtils.hasText(jwt);
			boolean isValidToken = tokenProvider.validateToken(jwt);
			if(isEmpty && isValidToken) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);
				User userDetails = customerUserDetailService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userDetails, null,java.util.Collections.emptyList());
				authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticate);
			}
		}catch(Exception ex) {
			logger.error("Could not set user authtication in security context",ex);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		String bearerToken = request.getHeader(com.example.demo.security.SecurityContant.HEADER_STRING);// header string means if u will see in postman in header sowe want that and it is authenticat
		
		if(org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith(com.example.demo.security.SecurityContant.TOKEN_PREFIX)) {
			return bearerToken.substring(7,bearerToken.length());
		}
		
		return null;
	}

}

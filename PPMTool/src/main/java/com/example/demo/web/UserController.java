package com.example.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.payload.JWTLoginSuccessResponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.UserService;
import com.example.demo.validator.UserValidator;

@Controller
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	MapValidationErrorService mapValidation;
	
	@Autowired
	UserValidator userValidate;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user , BindingResult result){
		
		userValidate.validate(user, result);
		ResponseEntity error=mapValidation.mapValidation(result);
		if(error!=null)
			return error;
		User userResult  = userService.saveUser(user);
		return new ResponseEntity<User>(userResult,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUse(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
		
	ResponseEntity error = mapValidation.mapValidation(result);
	if(error!=null) {
		return error;
	}
	Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
			);
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = com.example.demo.security.SecurityContant.TOKEN_PREFIX + tokenProvider.generateTheToken(authentication);
	
	return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}
	
	
}

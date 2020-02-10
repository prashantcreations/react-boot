package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.exception.UserNameAlreadyExistException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired	
	private BCryptPasswordEncoder bCryptPasswordEncoder;// for we have create a bean in mainclass
	
	
	
	public User saveUser(User user) {
		try {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserName(user.getUserName());
		return userRepo.save(user);
		}catch(Exception ex) {
			throw new UserNameAlreadyExistException("This User "+user.getUsername() +" Alredy Exist. Please Try with Some Another Name");
		}
	}

}

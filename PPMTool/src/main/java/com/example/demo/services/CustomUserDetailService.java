package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Our pojo object 
		User user = userRepository.findByUserName(username);
		if(user==null) new UsernameNotFoundException("User Not found");
			return user;
	}
	
	@Transactional
	public User loadUserById(Long Id) {
		User user = userRepository.getById(Id);
		if(user==null) new UsernameNotFoundException("User Not found");
		return user;
	}
}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;
import java.lang.String;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUserName(String username);
	User getById(Long Id);
}

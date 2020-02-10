package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BackLog;

@Repository
public interface BackLogRepository extends CrudRepository<BackLog, Long>{
	
	BackLog findByProjectIdentification(String identier);

}

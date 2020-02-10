package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Project;
import java.lang.String;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
	Project findByProjectIdentification(String projectId);
	Iterable<Project> findByProjectLeader(String projectLeader);
	
	
}

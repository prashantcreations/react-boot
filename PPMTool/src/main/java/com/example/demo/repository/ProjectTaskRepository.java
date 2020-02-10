package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.ProjectTask;
import java.lang.String;
import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
	
	//List<ProjectTask> findByProjectidentificationOrderbyPriority(String id);
	
	List<ProjectTask> findByProjectIdentificationOrderByPriority(String Id);
	 ProjectTask findByProjectSequence(String projectSequence);
}

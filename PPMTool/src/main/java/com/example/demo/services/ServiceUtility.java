package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Project;
import com.example.demo.exception.ProjectIdException;

@Component
public class ServiceUtility 
{
	public void FindProject(Project project,String userName) {
		if(project==null) {
			throw new ProjectIdException("Project Id :- "+ project.getProjectIdentification() +" do not exists "); 
		}
		if(!project.getProjectLeader().equals(userName)) {
			throw new ProjectIdException("Project not found in your account");
		}
	}

}

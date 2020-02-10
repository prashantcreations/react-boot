package com.example.demo.services;

import java.util.Iterator;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.exception.ProjectIdException;
import com.example.demo.repository.BackLogRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepo; 
	@Autowired
	private BackLogRepository backLogRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ServiceUtility serviceUtility;
	
	
	public Project createProject(Project project,String userName){
		if(project.getId()!=null) {
			Project existingProject = projectRepo.findByProjectIdentification(project.getProjectIdentification());
			if(existingProject!=null && (!existingProject.getProjectLeader().equals(userName))) {
				throw new ProjectIdException("Project not found in your account");
			}else if(existingProject==null) {
				throw new ProjectIdException("Project id not exist so cant update");
			}
		}
		
		try {
			User user =userRepo.findByUserName(userName);
			// setting user details in project for mapping one to many
			
			project.setUser(user);
			project.setProjectLeader(user.getUsername());
			
			// for uppercase just we are again setting this 
			project.setProjectIdentification(project.getProjectIdentification().toUpperCase());
			
			
			//at the time of crate there will not any project id so 
			
			if(project.getId()==null) {
				BackLog backlog = new BackLog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentification(project.getProjectIdentification().toUpperCase());
			}else if(project.getId()!=null) {
				// for set the current backlog for we wll not get the null backlog in response
				project.setBacklog(backLogRepo.findByProjectIdentification(project.getProjectIdentification().toUpperCase()));
			}
			
			
			
			return projectRepo.save(project);
		
		  }catch(Exception ex) { 
			  throw new ProjectIdException("Project Id :- "+project.getProjectIdentification().toUpperCase()+" alredy exists "); }
		} 
	
	public Project findByProjectIdentification(String projectId,String userName){
		Project project = projectRepo.findByProjectIdentification(projectId.toUpperCase());
		serviceUtility.FindProject(project,userName);
		return projectRepo.findByProjectIdentification(projectId.toUpperCase());
	}
	
	
	
	public Iterable<Project> findAll(String userName) {
		return projectRepo.findByProjectLeader(userName);
	}
	
	public void deleteProjectById(String projectId,String userName) {
		Project project = projectRepo.findByProjectIdentification(projectId.toUpperCase());
		serviceUtility.FindProject(project,userName);
		projectRepo.delete(project);
	}
	
	public void updateProjectById(Project updateProject) {
		Project project = projectRepo.findByProjectIdentification(updateProject.getProjectIdentification().toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project Id :- "+ updateProject.getProjectIdentification().toUpperCase() +" do not exists "); 
		}
		project.setPrjectName(updateProject.getPrjectName());
		project.setDesc(updateProject.getDesc());
		projectRepo.save(project);
	}

	
}

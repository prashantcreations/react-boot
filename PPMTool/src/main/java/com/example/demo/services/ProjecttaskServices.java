package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectTask;
import com.example.demo.exception.ProjectBacklogException;
import com.example.demo.repository.BackLogRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ProjectTaskRepository;

@Service
public class ProjecttaskServices {
	
	@Autowired
	BackLogRepository backLogRepo;
	@Autowired
	ProjectTaskRepository projectTaskRepo;
	@Autowired
	ProjectRepository projectRepo;
	@Autowired
	ProjectService projectService;
	
	
	public ProjectTask addProjectTask(String projectIdentification,ProjectTask projectTask,String userName) {
			BackLog backlog= projectService.findByProjectIdentification(projectIdentification,userName).getBacklog();
			
			projectTask.setBacklog(backlog);
			
			Integer projectSequence =backlog.getPTSequence();
			projectSequence = projectSequence + 1 ;
			backlog.setPTSequence(projectSequence);
			
			projectTask.setProjectSequence(projectIdentification+"-"+projectSequence);
			projectTask.setProjectIdentification(projectIdentification);
			
			if(projectTask.getPriority()==null || projectTask.getPriority()==0) {
				projectTask.setPriority(3);
			}
			
			if(projectTask.getStatus()==null || projectTask.getStatus()=="") {
				projectTask.setStatus("TO-DO");
			}
		return projectTaskRepo.save(projectTask);
	}
	
	
	public List<ProjectTask> findProjectTask(String backlogId,String userName) {
			
			Project project  = projectService.findByProjectIdentification(backlogId,userName);
			if(project==null) {
				throw new ProjectBacklogException("Project not found exception  "+ backlogId);
			}
		return projectTaskRepo.findByProjectIdentificationOrderByPriority(backlogId);
	}
	
	public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id,String userName) {
		
		projectService.findByProjectIdentification(backlog_id, userName);
		ProjectTask projectTask = projectTaskRepo.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id);
		}
		if(!projectTask.getProjectIdentification().equals(backlog_id)) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id + "not exist in project "+backlog_id);
		}
		return projectTask;
	}
	
	public ProjectTask updatePTByPrjojectSequence(String backlog_id,String pt_id,ProjectTask updatedProjectTask,String userName) {
		
		projectService.findByProjectIdentification(backlog_id, userName);
		ProjectTask projectTask = projectTaskRepo.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id);
		}
		if(!projectTask.getProjectIdentification().equals(backlog_id)) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id + "not exist in project "+backlog_id);
		}
		projectTask=updatedProjectTask;
		return projectTaskRepo.save(projectTask);
	}


	public void deleteProjectTaskBySequenceNumber(String backlog_id, String pt_id,String userName) {
		// TODO Auto-generated method stub
		projectService.findByProjectIdentification(backlog_id, userName);
		ProjectTask projectTask = projectTaskRepo.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id);
		}
		if(!projectTask.getProjectIdentification().equals(backlog_id)) {
			throw new ProjectBacklogException("Project Task not exist "+pt_id + "not exist in project "+backlog_id);
		}
		projectTaskRepo.delete(projectTask);
	}
}

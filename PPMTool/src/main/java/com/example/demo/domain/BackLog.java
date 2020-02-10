package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="back_log")
public class BackLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="_id")
	private Integer id;
	private Integer PTSequence = 0 ;
	private String projectIdentification;
	// eager load all relationship on first call
	//laze not load all relationship unless we llnot call
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id",nullable = false)//project_id work as forgine key
	@JsonIgnore
	private Project project;// this refrence name should be same as we pass in backlog onetoone mappring 
	//onetomany one backlog can contains many project task
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH,mappedBy = "backlog", orphanRemoval = true)
	private List<ProjectTask> projectTaskList= new ArrayList();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPTSequence() {
		return PTSequence;
	}
	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}
	public String getProjectIdentification() {
		return projectIdentification;
	}
	public void setProjectIdentification(String projectIdentification) {
		this.projectIdentification = projectIdentification;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<ProjectTask> getProjectTaskList() {
		return projectTaskList;
	}
	public void setProjectTaskList(List<ProjectTask> projectTaskList) {
		this.projectTaskList = projectTaskList;
	}
	
	//onetoone with project
	//onetomany with project task
}

package com.example.demo.exception;

public class ProjectIdExceptionResponse {

	private String projectIdentification;
	
	public ProjectIdExceptionResponse(String projectIdentification){
		this.projectIdentification=projectIdentification;
	}

	public String getProjectIdentification() {
		return projectIdentification;
	}

	public void setProjectIdentification(String projectIdentification) {
		this.projectIdentification = projectIdentification;
	}
	

}

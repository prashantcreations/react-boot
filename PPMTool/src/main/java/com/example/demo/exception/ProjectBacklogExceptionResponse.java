package com.example.demo.exception;

public class ProjectBacklogExceptionResponse {
	
	private String backlogId;
	
	public ProjectBacklogExceptionResponse(String backlogId) {
		this.backlogId= backlogId;
	}

	public String getBacklogId() {
		return backlogId;
	}

	public void setBacklogId(String backlogId) {
		this.backlogId = backlogId;
	}
}

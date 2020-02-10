package com.example.demo.domain;

import java.util.ArrayList;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class User1 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id ;
	int name ;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "user")
	@JoinColumn(name = "user_id")
	ArrayList<Project1> project;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public ArrayList<Project1> getProject() {
		return project;
	}

	public void setProject(ArrayList<Project1> project) {
		this.project = project;
	}

}

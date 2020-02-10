package com.example.demo.domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Project1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name ;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "project")
	@JoinColumn(name = "project_id")
	ArrayList<User1> user ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<User1> getUser() {
		return user;
	}

	public void setUser(ArrayList<User1> user) {
		this.user = user;
	}



}

package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
//@Table(name="project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id") 
    private Integer id;
    @NotBlank(message = "Project name is required")
    private String prjectName;
    @NotBlank(message ="Project Identifier is required")
    @Size(min=2, max=10, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentification;
    @NotBlank(message = "Project description is required")
    @Column(name="_desc") 
    private String desc;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name="_start_date") 
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name="_end_date") 
    private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false,name="_created_at")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name="_updated_at")
    private Date updated_At;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private BackLog backlog;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",updatable = false,nullable = false)
    @JsonIgnore
    private User user;
    private String projectLeader;
    

    public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }


    public String getPrjectName() {
		return prjectName;
	}

	public void setPrjectName(String prjectName) {
		this.prjectName = prjectName;
	}

	public String getProjectIdentification() {
		return projectIdentification;
	}

	public void setProjectIdentification(String projectIdentification) {
		this.projectIdentification = projectIdentification;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BackLog getBacklog() {
		return backlog;
	}

	public void setBacklog(BackLog backlog) {
		this.backlog = backlog;
	}

	@PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
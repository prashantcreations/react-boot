package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@Table(name = "_user")
public class User implements UserDetails{
	/* UserDetails */
	/*Implementations are not used directly by Spring Security for security purposes.
	 * They simply store user information which is later encapsulated into Authentication
	 * objects. This allows non-security related user information 
	 * (such as email addresses, telephone numbers etc) to be stored in a convenient
	 * location.	
	*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "_id")
	private Long id; 
	
	@NotBlank(message = "Please Enter Username")
	@Column(name="_username",unique = true)
	@Email(message = "Please Enter Valid Email Address")
	private String userName;
	
	@NotBlank(message="Please Enter Full Name")
	@Column(name="_fullname")
	private String fullName;
	
	@NotBlank(message = "Please Enter Password")
	@Column(name="_password")
	private String password;
	
	
	@Column(name="_confirmpassword")
	@Transient
	private String confirmPassword;
	
	@Column(name="create_at")
	private Date create_at;
	@Column(name="update_at")
	private Date update_at;
	@OneToMany(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER , mappedBy = "user" ,orphanRemoval = true)
	private List<Project> projects = new ArrayList();

	
	
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@PrePersist
	void onCreate(){
		this.create_at = new Date();
	}
	
	@PreUpdate
	void onUpdate() {
		this.update_at = new Date();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}
	
	//UserDetail method

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

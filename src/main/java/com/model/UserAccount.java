package com.model;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UserAccount {

	@Id
	private String emailId;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
	private User user;

	public UserAccount() {
		super();
	}

	public UserAccount(String emailId, String password, User user) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.user = user;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserAccount [emailId=" + emailId + ", password=" + password + ", user=" + user + "]";
	}
	
	
	
}

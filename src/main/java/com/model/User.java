package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "userstable")
public class User {

	@Id
	@GeneratedValue
	private int userNo;
	
	private String userName;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@JsonManagedReference
	private UserAccount account;

	public User() {
	}

	public User(int userNo, String userName, UserAccount account) {
		this.userNo = userNo;
		this.userName = userName;
		this.account = account;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userName=" + userName + "]";
	}
	
	
}

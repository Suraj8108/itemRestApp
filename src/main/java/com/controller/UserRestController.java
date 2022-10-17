package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserAccountDao;
import com.dao.UserDao;
import com.model.User;
import com.model.UserAccount;

@RestController
public class UserRestController {

	@Autowired
	UserDao dao;
	
	@Autowired
	UserAccountDao accdao;
	
	@GetMapping("/getusers")
	public List<User> getUser(){
		
		return dao.findAll();
		
	}
	
	@GetMapping("/getaccounts")
	public List<UserAccount> getUserAccount(){
		return accdao.findAll();
	}
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) {
		
		UserAccount usacc = user.getAccount();
		usacc.setUser(user);
		
		dao.save(user);
		return "Successfully Added";
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable int id) {
		dao.deleteById(id);
		return "Deleted User successfully";
	}
	
	@PostMapping("/addacc")
	public String addAccount(@RequestBody UserAccount account) {
		
		User user = account.getUser();
		user.setAccount(account);
		
		accdao.save(account);
		return "Successfully Added";
		
	}
	
}

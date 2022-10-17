package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ItemDao;
import com.model.Item;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class ItemRestController {

	@Autowired
	ItemDao dao;
	
	@ApiOperation(value = "Welcome Home Message", notes = "This to check the connection", nickname = "Connection Test")
	@ApiResponse(code=200, message="Success Connection")
	@GetMapping("/homeinfo")
	public String getHomeInfo() {
		return "Home Reached";
	}
	
	@GetMapping("/getallitems")
	public List<Item> getAllItems(){
		return dao.findAll();
	}
	
	@GetMapping("/getbyid/{id}")
	public Item getById(@PathVariable int id) {
		
		Optional<Item> item = dao.findById(id);
		return item.get();
	}
	
	@PostMapping("/additem")
	public ResponseEntity<String> addItem(@RequestBody Item item){
		
		dao.save(item);
		return new ResponseEntity("Added Item", HttpStatus.OK);
	}
	
	
	@PatchMapping("/updateitem")
	public ResponseEntity<String> updateItem(@RequestBody Item item){
		
		dao.save(item);
		return new ResponseEntity("Update Successfully", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteitem/{itemid}")
	public String deleteItemById(@PathVariable int itemid) {
		dao.deleteById(itemid);
		return "Deleted";
	}
	
	@DeleteMapping("/deleteitem")
	public String deleteItem(@RequestBody Item item) {
		dao.delete(item);
		return "Deleted Successfully";
		
	}
	
	
}

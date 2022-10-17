package com.itemrestapp.itemrestapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import com.dao.ItemDao;
import com.model.Item;

@SpringBootTest
public class TestItem {

	@Autowired
	ItemDao dao;
	
	@BeforeAll
	static void startConnection() {
		System.out.println("Connection Build");
	}
	
	@BeforeEach
	void deleteAllEntries() {
		dao.deleteAll();
	}
	@Test
	public void checkItemInsert() {
		Item expc = new Item();
		expc.setItemName("Coffee");
		expc.setPrice(20.23f);
		expc.setQuantity(2);
		
		Item actual = dao.save(expc);
		
		assertEquals(expc.toString(), actual.toString());
	}
	
	@Test
	public void checkItemGet() {
		
		Item i1 = new Item("Coffee", 20.00f, 12);
		Item i2 = new Item("Tea", 27.00f, 10);
		Item i3 = new Item("Toy", 28.00f, 1);
		
		List<Item> expected = Arrays.asList(i1, i2, i3);
		
		dao.save(i1);
		dao.save(i2);
		dao.save(i3);
		
		List<Item> actual = dao.findAll();
		
//		System.out.println(actual);
//		System.out.println(expected);
		boolean result = expected.toString().equals(actual.toString());
		assertEquals(true, result);
	}
	
	@Test
	public void checkItemUpdate() {
		Item expc = new Item("Coffee", 20.00f, 10);
		
		dao.save(expc);
		
		expc.setPrice(21.00f);
		
		Item actual = dao.save(expc);
		
		//boolean result = expc.toString().equals(actual.toString());
		//assertEquals(true, result);
		System.out.println(actual);
		System.out.println(expc);
		assertEquals(expc.toString(), actual.toString());
		
	}
	
	@Test
	public void checkItemDelete() {
		
		Item i1 = new Item("Coffee", 29.00f, 56);
		
		Item saveItem = dao.save(i1);
		
		dao.delete(saveItem);
		
		List<Item> items = dao.findAllById(Arrays.asList(saveItem.getItemId()));
		
		assertEquals(0, items.size());
	
	}
	
	@Test 
	public void checkCountByProductName() {
		Item i1 = new Item("Coffee", 29.00f, 56);
		Item i2 = new Item("Coffee", 26.00f, 22);
		
		dao.save(i1);
		dao.save(i2);
		
		long count = dao.countByItemName("Coffee");
		
		assertEquals(2, count);
		
	}
	
	@AfterAll
	static void closeConnection() {
		System.out.println("Closed Connection");
	}
}

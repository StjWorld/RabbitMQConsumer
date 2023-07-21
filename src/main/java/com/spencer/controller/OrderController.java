package com.spencer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spencer.entity.Order;
import com.spencer.service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	
	@GetMapping
	public ResponseEntity<List<Order>> getAll(){
		List<Order> orders = service.getAll();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		Order created = service.create(order);
		return new ResponseEntity<Order>(created, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> delete(@PathVariable("id") Long id) {
		Optional<Order> removed = service.getOrderById(id);
		if(!removed.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	

}

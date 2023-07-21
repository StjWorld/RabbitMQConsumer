package com.spencer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencer.entity.Order;
import com.spencer.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo repo;
	
	public List<Order> getAll(){
		List<Order> orders = repo.findAll();
		
		if(orders.size() > 0) {
			return orders;
		}else {
			return new ArrayList<Order>();
		}
	}
	public Order create (Order order) {
		Order newOrder = new Order();
		newOrder.setProduct(order.getProduct());
		newOrder.setProductId(order.getProductId());
		newOrder.setQty(order.getQty());
		newOrder = repo.save(newOrder);
		return newOrder;
		}
		
	public Optional<Order> getOrderById(Long id){
		return repo.findById(id);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
		
	

}

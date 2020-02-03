package com.SpringShop.controller.api;

import com.SpringShop.entity.api.Order;
import com.SpringShop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/api/v1/order/save")
	public ResponseEntity<Boolean> place(@RequestBody Order order) {
		orderService.save(order);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}

	@GetMapping("/api/v1/order/get")
	public ResponseEntity<Iterable<com.SpringShop.entity.web.Order>> get() {
		Iterable<com.SpringShop.entity.web.Order> orders = orderService.findAll();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}

package com.SpringShop.service.api;

import com.SpringShop.entity.web.Order;
import com.SpringShop.entity.web.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
	
	Iterable<Order> findAll();
	
	Page<Order> findLatest(int page, int size);
	
	List<Order> findByUser(User user);
	
	Order findOne(Integer id);
	
	long countAll();
	
	void save(com.SpringShop.entity.api.Order order);
	
	void delete(Integer orderId);
	
}

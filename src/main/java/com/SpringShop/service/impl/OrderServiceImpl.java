package com.SpringShop.service.impl;

import com.SpringShop.entity.web.*;
import com.SpringShop.repository.OrderRepository;
import com.SpringShop.repository.ProductRepository;
import com.SpringShop.repository.UserRepository;
import com.SpringShop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Order> findLatest(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "created");
		return orderRepository.findLatest(pageRequest);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Order findOne(Integer id) {
		return orderRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public long countAll() {
		return orderRepository.count();
	}

	@Override
	@Transactional
	public void save(com.SpringShop.entity.api.Order order) {
		// Current user
		//User currentUser = userRepository.findByEmail(order.getUser().getEmail());
		User currentUser = userRepository.findByEmail("guest@gmail.com");
		// Set order
		Order dbOrder = new Order();
		dbOrder.setName(order.getName());
		dbOrder.setAddress(order.getAddress());
		dbOrder.setPhone(order.getPhone());
		dbOrder.setNote(order.getNote());
		dbOrder.setUser(currentUser);
		
		// Set items
		Item item;
		for (Product e : order.getProducts()) {
			item = new Item();
			item.setOrder(dbOrder);
			item.setProduct(productRepository.findOne(e.getId()));
			item.setQuantity(1); //Must change to real quantity
			item.setPrice(e.getPrice());
			dbOrder.addItem(item);
		}

		dbOrder = orderRepository.save(dbOrder);
	}

	@Override
	public void delete(Integer orderId) {
		orderRepository.delete(orderId);
	}

}

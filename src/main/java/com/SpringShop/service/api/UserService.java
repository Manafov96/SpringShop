package com.SpringShop.service.api;

import com.SpringShop.entity.web.User;

import java.util.List;

public interface UserService {

	List<User> findAll();
	
	User findOne(Integer id);
	
	long countAll();
	
	void delete(Integer id);
	
	com.SpringShop.entity.api.User checkLogin(com.SpringShop.entity.api.User user);
	
	boolean register(User user);
	
}

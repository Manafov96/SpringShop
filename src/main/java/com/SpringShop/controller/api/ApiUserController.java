package com.SpringShop.controller.api;

import com.SpringShop.entity.web.User;
import com.SpringShop.service.api.RoleService;
import com.SpringShop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/api/v1/login")
	public ResponseEntity<com.SpringShop.entity.api.User> login(@RequestBody com.SpringShop.entity.api.User user) {
		return new ResponseEntity<com.SpringShop.entity.api.User>(userService.checkLogin(user), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/api/v1/register")
	public ResponseEntity<Boolean> register(@RequestBody com.SpringShop.entity.api.User user) {
		User dbUser = new User();
		dbUser.setName(user.getName());
		dbUser.setEmail(user.getEmail());
		dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
		dbUser.addRole(roleService.findByName("ROLE_CUSTOMER"));
		
    	return new ResponseEntity<Boolean>(userService.register(dbUser), HttpStatus.CREATED);
	}

	@GetMapping(value = "/api/v1/register/getUsers")
	public ResponseEntity<Iterable<User>> get() {
		Iterable<com.SpringShop.entity.web.User> orders = userService.findAll();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}

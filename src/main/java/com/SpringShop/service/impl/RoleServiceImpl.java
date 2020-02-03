package com.SpringShop.service.impl;

import com.SpringShop.entity.web.Role;
import com.SpringShop.repository.RoleRepository;
import com.SpringShop.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
}

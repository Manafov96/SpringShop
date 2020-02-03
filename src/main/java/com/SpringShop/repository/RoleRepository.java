package com.SpringShop.repository;

import com.SpringShop.entity.web.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);

}

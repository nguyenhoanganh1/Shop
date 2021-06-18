package com.eshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eshop.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{
	
	@Query("SELECT r FROM Role r WHERE r.role = ?1")
	Role findByName(String name);

}

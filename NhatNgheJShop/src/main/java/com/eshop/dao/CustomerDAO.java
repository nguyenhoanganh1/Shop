package com.eshop.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eshop.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, String>{

	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	Customer findCustomer(String id);
	
	@Query("SELECT c FROM Customer c WHERE c.roles = ?1")
	List<Customer> findByRole(String role);

}
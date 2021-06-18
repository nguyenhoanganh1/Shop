package com.eshop.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eshop.entity.Customer;
import com.eshop.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{
	@Query("SELECT o FROM Order o WHERE o.customer = ?1")
	List<Order> findByCustomer(Customer user);
	
}

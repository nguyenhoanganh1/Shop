package com.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

}

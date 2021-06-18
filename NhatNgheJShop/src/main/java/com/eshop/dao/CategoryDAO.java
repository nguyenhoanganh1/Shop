package com.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

	

}

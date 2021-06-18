package com.eshop.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eshop.entity.Customer;
import com.eshop.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	@Query("SELECT c FROM Product c WHERE c.category.id = ?1")
	List<Product> findAllByCategory(Integer categoryId);

	@Query("SELECT o FROM Product o WHERE o.name LIKE %?1% OR o.category.name LIKE %?1% OR o.category.nameVN LIKE %?1%")
	List<Product> findByKeywords(String keywords);

	@Query("SELECT c FROM Product c ORDER BY size(c.orderDetails) DESC")
	List<Product> findByBestSeller(Pageable page);
	
	@Query("SELECT c FROM Product c WHERE c.discount > 0 ORDER BY c.discount DESC")
	List<Product> findBySaleOff();
	
	@Query("SELECT c FROM Product c WHERE c.clickCount > 0 ORDER BY c.clickCount DESC")
	List<Product> findByMostView();
	
	@Query("SELECT c FROM Product c WHERE c.special = true")
	List<Product> findBySpecial(Pageable page);
	
	@Query("SELECT c FROM Product c WHERE c.latest = true")
	List<Product> findByLatest();
	
	@Query("SELECT c FROM Product c WHERE c.id IN ?1")
	List<Product> findByIds(List<Integer> list);
	
	@Query("SELECT DISTINCT c.product FROM OrderDetail c WHERE c.order.customer = ?1")
	List<Product> findByCustomer(Customer user);

	
	
}

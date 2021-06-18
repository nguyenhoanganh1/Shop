package com.eshop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eshop.entity.Report;

public interface ReportDAO extends JpaRepository<Report, Serializable>{
	@Query("SELECT new Report(p.category, "
			+ " sum(p.unitPrice*p.quantity), "
			+ " sum(p.quantity), "
			+ " min(p.unitPrice), "
			+ " max(p.unitPrice), "
			+ " avg(p.unitPrice)) "
			+ " FROM Product p "
			+ " GROUP BY p.category"
			+ " ORDER BY sum(p.unitPrice*p.quantity) desc")
	List<Report> getInventoryByCategory();
	
	@Query("SELECT new Report(d.product.category, "
			+ " sum(d.unitPrice*d.quantity), "
			+ " sum(d.quantity), "
			+ " min(d.unitPrice), "
			+ " max(d.unitPrice), "
			+ " avg(d.unitPrice)) "
			+ " FROM OrderDetail d "
			+ " GROUP BY d.product.category")
	List<Report> getRevenueByCategory();
	@Query("SELECT new Report(d.order.customer, "
			+ " sum(d.unitPrice*d.quantity*(1 - d.discount)) AS Amount, "
			+ " sum(d.quantity), "
			+ " min(d.unitPrice), "
			+ " max(d.unitPrice), "
			+ " avg(d.unitPrice)) "
			+ " FROM OrderDetail d "
			+ " WHERE d.order.status=4"
			+ " GROUP BY d.order.customer"
			+ " ORDER BY Amount DESC")
	List<Report> getRevenueByCustomer(Pageable pager);
	
	@Query("SELECT new Report(year(d.order.orderDate) AS Year, "
			+ " sum(d.unitPrice*d.quantity*(1 - d.discount)), "
			+ " sum(d.quantity), "
			+ " min(d.unitPrice), "
			+ " max(d.unitPrice), "
			+ " avg(d.unitPrice)) "
			+ " FROM OrderDetail d "
			+ " WHERE d.order.status=4"
			+ " GROUP BY year(d.order.orderDate)"
			+ " ORDER BY Year")
	List<Report> getRevenueByYear();
	
	@Query("SELECT new Report(cast(ceiling(month(d.order.orderDate)/3.0) as int) AS Quarter, "
			+ " sum(d.unitPrice*d.quantity*(1 - d.discount)), "
			+ " sum(d.quantity), "
			+ " min(d.unitPrice), "
			+ " max(d.unitPrice), "
			+ " avg(d.unitPrice)) "
			+ " FROM OrderDetail d "
			+ " WHERE d.order.status=4"
			+ " GROUP BY cast(ceiling(month(d.order.orderDate)/3.0) as int)"
			+ " ORDER BY Quarter")
	List<Report> getRevenueByQuarter();
	
	@Query("SELECT new Report(month(d.order.orderDate) AS Month, "
			+ " sum(d.unitPrice*d.quantity*(1 - d.discount)), "
			+ " sum(d.quantity), "
			+ " min(d.unitPrice), "
			+ " max(d.unitPrice), "
			+ " avg(d.unitPrice)) "
			+ " FROM OrderDetail d "
			+ " WHERE d.order.status=4"
			+ " GROUP BY month(d.order.orderDate)"
			+ " ORDER BY Month")
	List<Report> getRevenueByMonth();
	
	@Query("SELECT count(o) FROM Order o WHERE o.status=?1")
	Long reportByStatus(int status);
	
	default Map<Integer, Long> getSummary(){
		int[] status = {-1, 0, 1, 2, 3, 4};
		Map<Integer, Long> map = new TreeMap<>();
		for(int key: status) {
			map.put(key, reportByStatus(key));
		}
		return map;
	}
	
}

package com.eshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Orderdetails", uniqueConstraints = {@UniqueConstraint(columnNames = {"orderid", "productid"})})
public class OrderDetail  implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name = "unitprice")
	Double unitPrice;
	Integer quantity;
	Double discount;
	
	@ManyToOne
	@JoinColumn(name = "orderid")
	Order order;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
}

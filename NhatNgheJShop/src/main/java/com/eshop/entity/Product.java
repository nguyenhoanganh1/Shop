package com.eshop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "Products")
public class Product  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String image;
	@Column(name = "unitprice")
	Double unitPrice;
	double discount;
	Integer quantity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "productdate")
	@Temporal(TemporalType.DATE)
	Date productDate;
	String description;
	boolean special;
	boolean latest = true;
	@Column(name = "clickcount")
	int clickCount;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;

	
}

package com.eshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
@Table(name = "Roles")
public class Role  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String role;
	
	@Override
	public String toString() {
		return this.role;
	}
}

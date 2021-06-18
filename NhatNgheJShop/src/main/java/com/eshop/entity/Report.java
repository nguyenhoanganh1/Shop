package com.eshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter 
@Setter
@AllArgsConstructor 
@NoArgsConstructor

@Entity
public class Report  implements Serializable{
	@Id
	Serializable group;
	Double sum;
	Long count;
	Double min;
	Double max;
	Double avg;
	
	
}

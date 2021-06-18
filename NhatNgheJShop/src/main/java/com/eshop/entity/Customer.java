package com.eshop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
@Table(name = "Customers")
public class Customer implements Serializable {
	
	@Id
	private String id;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	private boolean activated = false;
	@Column(name="onetimepassword")
	private String oneTimePassword;
	@Column(name="otptime")
	private Date otpTime;
	@Enumerated(EnumType.STRING)
    private Provider provider;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Order> orders;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="roledetails", joinColumns = @JoinColumn(name = "customerid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
		
	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	@Override
	public String toString() {
		return this.fullname;
	}
	
	public boolean isOTPRequired() {
		if(this.oneTimePassword == null)
		{
			return false;
		}
		long otpRequestedTimeInMillis = this.otpTime.getTime();
		if(otpRequestedTimeInMillis + OTP_VALID_DURATION < System.currentTimeMillis())
		{
			return false;
		}
		return true;
	}
	
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	
}

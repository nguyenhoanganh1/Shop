package com.eshop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eshop.entity.Customer;
import com.eshop.entity.Provider;
import com.eshop.entity.Role;
@SuppressWarnings("serial")
public class CustomerDetails implements UserDetails {
	
	@Autowired
	private Customer customer;
	
	public CustomerDetails(Customer customer) {
		this.customer = customer;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = customer.getRoles(); 
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		if(customer.isOTPRequired())
		{
			return customer.getOneTimePassword();
		}
		return customer.getPassword();
	}
	
	public String getFullname() {
		return customer.getFullname();
	}
	
	
	public String getPhoto() {
		return customer.getPhoto();
	}
	
	public Set<Role> getRole() {
		return customer.getRoles();
	}
	
	public Provider getProvider() {
		return customer.getProvider();
	}
	
	
	public String getId() {
		return customer.getId();
	}

	@Override
	public String getUsername() {
		return customer.getId();
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return customer.isActivated();
	}
	
	public Customer getcustomer() {
		return this.customer;
	}
}

package com.eshop.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eshop.dao.CustomerDAO;
import com.eshop.entity.Customer;

public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	private CustomerDAO cdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = cdao.findCustomer(username);
		if(customer == null)
		{
			throw new UsernameNotFoundException("Không Tìm Thấy Người Dùng");
		}
		return new CustomerDetails(customer);
	}

}

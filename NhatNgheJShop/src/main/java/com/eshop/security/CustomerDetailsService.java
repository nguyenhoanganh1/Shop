package com.eshop.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eshop.dao.CustomerDAO;
import com.eshop.entity.Customer;
@Service
public class CustomerDetailsService implements UserDetailsService  {
	
	@Autowired
    private CustomerDAO cdao;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = cdao.findCustomer(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find user with that username");
        }
         
        return new CustomerDetails(customer);
    }
}

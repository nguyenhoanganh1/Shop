package com.eshop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.eshop.entity.Customer;
import com.eshop.jwt.JwtUtil;
import com.eshop.service.CustomerService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private Customer customer;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authentication) throws IOException, ServletException {
//		
//		CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
//		Customer customer = customerDetails.getcustomer();
//		customerService.clearOTP(customer);
//		System.out.println(customerDetails.getFullname());
//		
//		super.onAuthenticationSuccess(request, response, chain, authentication);
//	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
			CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
			if(oauthUser != null)
			{
				String email = oauthUser.getEmail();
				String name = oauthUser.getName();
				String id = oauthUser.getId();
				customerService.CreateNewCustomerAfterOAthLoginSucces(id, name, email);
				System.out.println("onAuthenticationSuccess: " + oauthUser.getName());
			
			UrlPathHelper helper = new UrlPathHelper();
			String contextPath = helper.getContextPath(request);
			System.out.println("Context Path: " + contextPath);
			response.sendRedirect("/");
			}
			
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}

package com.eshop.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.eshop.dao.CustomerDAO;
import com.eshop.entity.Customer;
import com.eshop.service.CustomerService;

@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private CustomerDAO cdao;
	
	
	@Autowired
	private CustomerService customerService;
	
	public BeforeAuthenticationFilter() {
		super.setUsernameParameter("username");
		super.setPasswordParameter("password");
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/account/login", "POST"));
	}

	// Set authenticationManager để sử dụng @Component

	@Autowired
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Autowired
	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		super.setAuthenticationSuccessHandler(successHandler);
	}

	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		Customer customer = customerService.getCustomerById(username);
		System.out.println("Customer id: " + customer.getId());
		/*
		 * if (customer != null) { float spamScore = getGoogleRecaptchaScore();
		 * if(spamScore < 0.5) { throw new InsufficientAuthenticationException("OTP"); }
		 * }
		 * 
		 * String recaptchaFormResponse = request.getParameter("g-recaptcha-response");
		 * RecaptchaHandlerV3 handler = new RecaptchaHandlerV3(); try { float score =
		 * handler.verify(recaptchaFormResponse); if (score < 0.5) {
		 * 
		 * request.getRequestDispatcher("/account/otp_confirm").forward(request,
		 * response); }
		 * 
		 * } catch (InvalidReCaptchaTokenException | ServletException | IOException e) {
		 * try { response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		 * } catch (IOException e1) {
		 * 
		 * e1.printStackTrace(); } }
		 * System.out.println("Before processing authentication !!!");
		 */
		return super.attemptAuthentication(request, response);
	}
	
}

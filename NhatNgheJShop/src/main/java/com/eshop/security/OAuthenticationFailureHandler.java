package com.eshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("Authentication Failure");
		String username = request.getParameter("username");
		System.out.println(" onAuthentication username: " + username);
		String failureRedirect = "/account/login?error&username=" + username ;
		if(exception.getMessage().contains("OTP"))
		{
			failureRedirect = "/account/otp_confirm?otp=true&username=" + username ;
		}
		super.setDefaultFailureUrl("/account/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}
}

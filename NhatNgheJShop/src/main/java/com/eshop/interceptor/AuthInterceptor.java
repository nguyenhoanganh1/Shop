package com.eshop.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;

import com.eshop.entity.Customer;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		
		String error = "";
		if(user == null) {
			error = "Vui lòng đăng nhập!";
		}
		else {
			if(uri.startsWith("/admin/")) {
				error = "Vui lòng đăng nhập với tài khoản admin!";
			} 
		}
		
		if(error.length() > 0) {
			session.setAttribute("auth-uri", uri);
			response.sendRedirect("/account/login?message=" + URLEncoder.encode("Vui lòng đăng nhập", "utf-8"));
			return false;
		}
		return true;
	}
}

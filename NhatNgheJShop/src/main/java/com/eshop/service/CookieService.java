package com.eshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.utils.XJson;
import com.eshop.utils.XStr;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	public Cookie create(String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(days * 24 * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}

	public void delete(String name) {
		this.create(name, "", 0);
	}

	public String getValue(String name, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return defaultValue;
	}

	/*
	 * Ghi nhận các mặt hàng đã xem
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getVisitedIds() {
		String json = XStr.decB64(getValue("visits", XStr.encB64("[]")));
		return XJson.toObject(json, List.class);
	}

	public void setVisitedIds(List<Integer> ids) {
		this.create("visits", XStr.encB64(XJson.toJson(ids)), 10);
	}

	/*
	 * Ghi nhận các mặt hàng yêu thích
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getFavoriteIds() {
		String json = XStr.decB64(getValue("favos", XStr.encB64("[]")));
		return XJson.toObject(json, List.class);
	}

	public void setFavoriteIds(List<Integer> ids) {
		this.create("favos", XStr.encB64(XJson.toJson(ids)), 10);
	}
	
	/* Ghi nhớ tài khoản */
	@SuppressWarnings("unchecked")
	public Map<String, String> getUserSecurity() {
		String json = XStr.decB64(getValue("remember-me", XStr.encB64("{}")));
		return XJson.toObject(json, Map.class);
	}

	/* Ghi nhớ tài khoản */
	@SuppressWarnings("unchecked")
	public Map<String, String> getUser() {
		String json = XStr.decB64(getValue("user", XStr.encB64("{}")));
		return XJson.toObject(json, Map.class);
	}

	public Map<String, String> setUser(String username, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		this.create("user", XStr.encB64(XJson.toJson(map)), 30);
		return map;
	}
}

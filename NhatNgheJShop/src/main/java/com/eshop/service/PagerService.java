package com.eshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagerService {
	@Autowired
	HttpServletRequest request;
	
	@SuppressWarnings("unchecked")
	public Pageable	getPageable() {
		Map<String, Integer> pager = (Map<String, Integer>)request.getSession().getAttribute("pager");
		if(pager == null) {
			pager = new HashMap<String, Integer>();
			pager.put("size", 10);
			pager.put("page", 0);
			request.getSession().setAttribute("pager", pager);
		}
		
		if(request.getParameter("page") != null) {
			int page = Integer.valueOf(request.getParameter("page"));
			
			if(page < 0) {
				pager.put("page", pager.get("count") - 1);
			}
			else if(page >= pager.get("count")) {
				pager.put("page", 0);
			}
			else {
				pager.put("page", page);
			}
		}
		return PageRequest.of(pager.get("page"), pager.get("size"));
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Page<T> page) {
		Map<String, Integer> pager = (Map<String, Integer>)request.getSession().getAttribute("pager");
		pager.put("count", page.getTotalPages()); /* số trang */
		pager.put("total", (int)page.getTotalElements()); /* tổng số thực thể */
		pager.put("rows", page.getNumberOfElements()); /* số thực thể hiện tại */
		return page.toList();
	}
}

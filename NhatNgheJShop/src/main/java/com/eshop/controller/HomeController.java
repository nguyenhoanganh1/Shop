package com.eshop.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.dao.CategoryDAO;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Category;
import com.eshop.entity.Product;

@Controller
public class HomeController {
	@Autowired
	CategoryDAO cdao;
	@Autowired
	ProductDAO pdao;
	
	@RequestMapping("/error/403")
	public String page403() {
		return "403/403";
	}
	
	
	@RequestMapping({"/","/home/index"})
	public String index(Model model) {
		List<Category> list = cdao.findAll();
		Collections.shuffle(list);
		list = list.subList(0, 4);
		for (Category category : list) {
			Collections.shuffle(category.getProducts());
			category.setProducts(category.getProducts().subList(0, 4));
		}
		model.addAttribute("list", list);
		
		List<Product> prods = pdao.findBySpecial(PageRequest.of(0,6));
		model.addAttribute("cateProduct", prods);
		return "home/index";
	}
	
	@RequestMapping("/home/about")
	public String about() {
		return "home/about";
	}
	
	@RequestMapping("/home/contact")
	public String contact() {
		return "home/contact";
	}
	
	@RequestMapping("/home/feedback")
	public String feedback() {
		return "home/feedback";
	}
	
	@RequestMapping("/home/faq")
	public String faq() {
		return "home/faq";
	}
	
	@ResponseBody
	@RequestMapping("/home/lang")
	public void lang() {}
}

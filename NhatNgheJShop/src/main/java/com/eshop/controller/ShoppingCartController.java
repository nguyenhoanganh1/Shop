package com.eshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartService cart;
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		model.addAttribute("cart", cart.getItems());
		return "cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/info")
	public Map<String, Object> info() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", cart.getCount());
		map.put("amount", cart.getAmount());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/cart/add/{id}")
	public Map<String, Object> add(@PathVariable("id") Integer id) {
		cart.add(id);
		return this.info();
		//return "forward:/cart/info";
	}
	@ResponseBody
	@RequestMapping("/cart/remove/{id}")
	public Map<String, Object> remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return this.info();
		//return "forward:/cart/info";
	}
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}")
	public Map<String, Object> update(@PathVariable("id") Integer id, 
			@PathVariable("qty") Integer qty) {
		cart.update(id, qty);
		return this.info();
		//return "forward:/cart/info";
	}
	@ResponseBody
	@RequestMapping("/cart/clear")
	public Map<String, Object> clear() {
		cart.clear();
		return this.info();
		//return "forward:/cart/info";
	}
}
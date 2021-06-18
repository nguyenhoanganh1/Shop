package com.eshop.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.dao.OrderDAO;
import com.eshop.entity.Order;
import com.eshop.service.PagerService;

@Controller
@RequestMapping("/admin/order")
public class OrderAController {
	@Autowired
	OrderDAO cdao;
	
	@Autowired
	PagerService pager;
	
	@RequestMapping("index")
	public String index(Model model) {
		Order cus = new Order();
		List<Order> items = cdao.findAll();
		//model.addAttribute("items", items);	
		model.addAttribute("items", pager.getList(cdao.findAll(pager.getPageable())));
		model.addAttribute("form", cus);
		return "admin/order/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Order cate = cdao.getOne(id);
		model.addAttribute("form", cate);
		List<Order> items = cdao.findAll();
		model.addAttribute("items", items);		
		return "admin/order/index";
	}
	
	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Order form) {
		cdao.save(form);
		List<Order> items = cdao.findAll();
		model.addAttribute("items", items);
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping("update")
	public String update(Model model,RedirectAttributes params , @ModelAttribute("form") Order form) {
		cdao.save(form);			
		
		/*
		 * params.addAttribute("id", form.getId());
		 * params.addAttribute("name",form.getName()); 
		 * params.addAttribute("nameVN",		 
		 * * form.getNameVN()); return "redirect:/admin/order/index";
		 */
		 
	return "redirect:/admin/order/edit/"+form.getId();
	}
	
	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		Order cate = cdao.getOne(id);
		cdao.delete(cate);	
		return "redirect:/admin/order/index";
	}
	
	
	@ModelAttribute("status")
	public Map<Integer, String> getStatus(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(-1, "Canceled");
		map.put(0, "New");
		map.put(1, "Confirming");
		map.put(2, "Confirmed");
		map.put(3, "Shipping");
		map.put(4, "Completed");		
		return map;
		
	}
}

package com.eshop.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.dao.CustomerDAO;
import com.eshop.dao.RoleDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Role;
import com.eshop.service.UploadService;

@Controller
@RequestMapping("/admin/master")
public class MasterAController {

	@Autowired
	UploadService upload;
	

	@Autowired
	private CustomerDAO cdao;

	@RequestMapping("/index")
	public String index(Model model) {
		Customer cus = new Customer();
		List<Customer> items = cdao.findAll();
		model.addAttribute("items", items);		
		model.addAttribute("form", cus);
		return "admin/master/index";
	}
	
	@RequestMapping("/update")
	public String update(Model model, @ModelAttribute("form") Customer form) throws UnsupportedEncodingException {
		cdao.save(form);
		return "redirect:/admin/customer/edit/"+form.getId()+"?message" + URLEncoder.encode("Phân quyền thành công","utf-8");
	}

	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Customer cus = cdao.getOne(id);
		List<Customer> items = cdao.findAll();
		model.addAttribute("form", cus);
		model.addAttribute("items", items);
		return "admin/master/index";
	}
	

}

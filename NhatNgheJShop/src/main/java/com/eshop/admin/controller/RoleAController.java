package com.eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.dao.RoleDAO;
import com.eshop.entity.Role;

@Controller
@RequestMapping("/admin/role")
public class RoleAController {
	
	@Autowired
	private RoleDAO rdao;
	
	@RequestMapping("index")
	public String index(Model model) {
		Role role = new Role();
		List<Role> items = rdao.findAll();
		model.addAttribute("items", items);		
		model.addAttribute("form", role);
		return "admin/role/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Role role = rdao.getOne(id);
		model.addAttribute("form", role);
		List<Role> items = rdao.findAll();
		model.addAttribute("items", items);		
		return "admin/role/index";
	}
	
	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Role form) {
		rdao.save(form);
		List<Role> items = rdao.findAll();
		model.addAttribute("items", items);
		return "redirect:/admin/role/index";
	}
	
	@RequestMapping("update")
	public String update(Model model, @ModelAttribute("form") Role form) {
		rdao.save(form);
		List<Role> items = rdao.findAll();
		model.addAttribute("items", items);
		return "redirect:/admin/role/index";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		rdao.deleteById(id);
		List<Role> roles = rdao.findAll();
		model.addAttribute("items", roles);
		return "redirect:/admin/role/index";
	}

}

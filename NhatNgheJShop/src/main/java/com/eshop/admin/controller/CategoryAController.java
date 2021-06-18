package com.eshop.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.dao.CategoryDAO;
import com.eshop.entity.Category;

@Controller
@RequestMapping("/admin/category")
public class CategoryAController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryAController.class);
	
	@Autowired
	CategoryDAO cdao;
	
	@RequestMapping("index")
	public String index(Model model) {
		LOGGER.trace("Entering Method Category Admin Controller.");
		Category cus = new Category();
		List<Category> items = cdao.findAll();
		LOGGER.debug("Debug Items: "+items);
		model.addAttribute("items", items);		
		model.addAttribute("form", cus);
		LOGGER.info("INFO list category");
		return "admin/category/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category cate = cdao.getOne(id);
		model.addAttribute("form", cate);
		List<Category> items = cdao.findAll();
		model.addAttribute("items", items);		
		return "admin/category/index";
	}
	
	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Category form) {
		cdao.save(form);
		List<Category> items = cdao.findAll();
		model.addAttribute("items", items);	
		return "redirect:/admin/category/index";
	}
	
	@RequestMapping("update")
	public String update(Model model,RedirectAttributes params , @ModelAttribute("form") Category form) {
		cdao.save(form);	
		/*
		 * params.addAttribute("id", form.getId()); params.addAttribute("name",
		 * form.getName()); params.addAttribute("nameVN", form.getNameVN()); return
		 * "redirect:/admin/category/index";
		 */
		return "redirect:/admin/category/edit/"+form.getId();
	}
	
	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		Category cate = cdao.getOne(id);
		cdao.delete(cate);	
		return "redirect:/admin/category/index";
	}
	
}

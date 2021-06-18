package com.eshop.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.dao.CategoryDAO;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Category;
import com.eshop.entity.Product;
import com.eshop.service.PagerService;
import com.eshop.service.UploadService;

@Controller
@RequestMapping("/admin/product")
public class ProductAController {
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	UploadService upload;
	
	@Autowired
	PagerService pager;
	
	@RequestMapping("index")
	public String index(Model model) {
		
		//List<Product> items = pdao.findAll();
		model.addAttribute("items", pager.getList(pdao.findAll(pager.getPageable())));
		//model.addAttribute("items", items);	
		Product cus = new Product();
		model.addAttribute("form", cus);
		return "admin/product/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product cate = pdao.getOne(id);
		model.addAttribute("form", cate);
		List<Product> items = pdao.findAll();
		model.addAttribute("items", items);		
		return "admin/product/index";
	}
	
	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Product form) {
		pdao.save(form);
		List<Product> items = pdao.findAll();
		model.addAttribute("items", items);	
		return "redirect:/admin/product/index";
	}
	
	@RequestMapping("update")
	public String update(Model model,RedirectAttributes params , @ModelAttribute("form") Product form,
			@RequestParam("image_file") MultipartFile image ) {
		File file = upload.save(image, "/static/images/items/");
		if(file != null) {
			form.setImage(file.getName());
		}
		pdao.save(form);	
		/*
		 * params.addAttribute("id", form.getId()); params.addAttribute("name",
		 * form.getName()); params.addAttribute("nameVN", form.getNameVN()); return
		 * "redirect:/admin/product/index";
		 */
		return "redirect:/admin/product/edit/"+form.getId();
	}
	
	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		Product cate = pdao.getOne(id);
		pdao.delete(cate);	
		return "redirect:/admin/product/index";
	}
	
	
	@Autowired
	CategoryDAO cdao;
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return cdao.findAll();
	}
}

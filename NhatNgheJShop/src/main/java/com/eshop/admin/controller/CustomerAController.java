package com.eshop.admin.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.dao.CustomerDAO;
import com.eshop.dao.RoleDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Provider;
import com.eshop.entity.Role;
import com.eshop.service.MailerService;
import com.eshop.service.UploadService;
import com.eshop.utils.PasswordEncoder;

@Controller
@RequestMapping("/admin/customer")
public class CustomerAController {
	@Autowired
	CustomerDAO cdao;

	@Autowired
	RoleDAO rdao;

	@Autowired
	UploadService upload;

	@Autowired
	MailerService mailer;

	@RequestMapping("index")
	public String index(Model model) {
		Customer cus = new Customer();
		List<Customer> items = cdao.findAll();
		List<Role> listRole = rdao.findAll();

		Map<Integer, String> roles = new HashMap<Integer, String>(); 
		for (Role role : listRole) 
		{ 
			roles.put(role.getId(), role.getRole()); 
		}
		model.addAttribute("roles", roles);
		model.addAttribute("items", items);
		model.addAttribute("form", cus);
		return "admin/customer/index";
	}
	


	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		Customer cate = cdao.getOne(id);
		List<Role> listRole = rdao.findAll();

		Map<Integer, String> roles = new HashMap<Integer, String>(); 
		for (Role role : listRole) 
		{ 
			roles.put(role.getId(), role.getRole()); 
		}
		model.addAttribute("roles", roles);
		model.addAttribute("form", cate);
		List<Customer> items = cdao.findAll();
		model.addAttribute("items", items);			
		return "admin/customer/index";
	}

	@RequestMapping("create")
	public String create(Model model, @ModelAttribute("form") Customer form,
			@RequestParam("photo_file") MultipartFile photo, @RequestParam("confirm") String confirm)
			throws UnsupportedEncodingException {
		if (!form.getPassword().equals(confirm)) {
			return "redirect:/admin/customer/index?message="
					+ URLEncoder.encode("X??c nh???n m???t kh???u kh??ng gi???ng nhau", "utf-8");
		} 
		else {
			try {
				Customer user = cdao.findById(form.getId()).get();	
				if(user != null) {
					model.addAttribute("message", user.getId());
				}
			} 
			catch (Exception e) {
				try {
					File file = upload.save(photo, "/static/images/users/");
					form.setPhoto(file == null ? "user.png" : file.getName());
					form.setProvider(Provider.local);
					String from = "EShop <hoanganh25022@gmail.com>";
					String to = form.getEmail();
					String subject = "Welcome to website EShop";
					String body = "Ch??c m???ng b???n ???? tr??? th??nh th??nh vi??n c???a ch??ng t??i. "
							+ "L??ng ????ng nh???p b???ng t??i kho???n<hr/>" + "Username: " + form.getId() + "<hr/>"
							+ "Password: " + form.getPassword();
					mailer.send(from, to, subject, body);
					model.addAttribute("message", "????ng k?? th??nh c??ng. Vui l??ng ki???m tra email");
					form.setPassword(PasswordEncoder.setBCryptPasswordEncoder(form.getPassword()));
					cdao.save(form);
					return "redirect:/admin/customer/index?message="
							+ URLEncoder.encode("Th??m m???i th??nh c??ng", "utf-8");
				} 
				catch (Exception e2) {
					model.addAttribute("message", e2);
				}
				
			} 
			return "admin/customer/index";
		}

	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile photo, @RequestParam("confirm")String confirm) throws UnsupportedEncodingException {

		/*
		 * params.addAttribute("id", form.getId()); params.addAttribute("name",
		 * form.getName()); params.addAttribute("nameVN", form.getNameVN()); return
		 * "redirect:/admin/customer/index";
		 */
		try {
			if (!cdao.existsById(user.getId())) {
				user.setId(null);
				model.addAttribute("message", "Kh??ch h??ng kh??ng t???n t???i!");
			}
			else if(!confirm.equals(user.getPassword())) {
				return "redirect:/admin/customer/edit/"+user.getId()+"?message=" + URLEncoder.encode("M???t kh???u ph???i gi???ng nhau","utf-8");
			}
			else {	
				
				File file = upload.save(photo, "/static/images/users/");
				if (file != null) {
					user.setPhoto(file.getName());
				}
			
				user.setPassword(PasswordEncoder.setBCryptPasswordEncoder(user.getPassword()));	
								
				cdao.save(user);
				return "redirect:/admin/customer/edit/"+user.getId()+"?message=" + URLEncoder.encode("C???p nh???t kh??ch h??ng th??nh c??ng","utf-8");
			}
		} catch (Exception e) {
			return "redirect:/admin/customer/index?message=" + URLEncoder.encode("c???p nh???t th???t b???i","utf-8");
		}
		return "redirect:/admin/customer/index";
	}
	@RequestMapping("delete/{id}")
	public String delete(Model model, @PathVariable("id") String id) throws UnsupportedEncodingException {
		try {
			Customer cate = cdao.getOne(id);
			cdao.delete(cate);

		} catch (Exception e) {
			model.addAttribute("message", "X??a th???t b???i");
		}
		return "redirect:/admin/customer/index?message=" + URLEncoder.encode("X??a th??nh c??ng","utf-8");
		
	}

	
}

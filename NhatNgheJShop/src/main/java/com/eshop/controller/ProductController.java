package com.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.bean.MailInfo;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Product;
import com.eshop.service.CookieService;
import com.eshop.service.MailerService;
import com.eshop.utils.XStr;

@Controller
public class ProductController {
	@Autowired
	ProductDAO dao;
	@Autowired
	MailerService mailer;
	@Autowired
	CookieService cookie;

	@RequestMapping("/product/find-by-category/{id}")
	public String listByCategory(@PathVariable("id") Integer categoryId, Model model) {
		List<Product> list = dao.findAllByCategory(categoryId);
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/find-by-keywords")
	public String listByKeywords(@RequestParam("keywords") String keywords, Model model) {
		List<Product> list = dao.findByKeywords(keywords);
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/find-by-special/{special}")
	public String listBySpecials(@PathVariable("special") Integer special, Model model) {
		List<Product> list;
		switch (special) {
		case 0:
			list = dao.findByBestSeller(PageRequest.of(0, 9));
			break;
		case 1:
			list = dao.findByLatest();
			break;
		case 2:
			list = dao.findBySaleOff();
			break;
		case 3:
			list = dao.findByMostView();
			break;
		case 4:
			list = dao.findBySpecial(PageRequest.of(0, 9));
			break;
		default:
			list = dao.findAll();
		}
		model.addAttribute("cateProduct", list);
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		// lấy id sản phẩm để hiện chi tiết sp và tằng số lượng người đã xem sp
		// ghi nhận số lần click
		Product p = dao.getOne(id);
		p.setClickCount(p.getClickCount() + 1);
		dao.save(p);

		// lấy cookie cũ nếu cookie cũ chưa có thì lưu vào
		// String s = cookie.getValue("visits", "");
		// List<String> ids = Arrays.asList(cookie.getValue("visits",
		// "").split("[,]+"));
		String ids = XStr.decB64(cookie.getValue("visits", ""));
		// mã hóa các ký tự đặc biệt để cookie k bị lỗi
		if (!ids.contains(id.toString())) {
			ids += "," + id;
			cookie.create("visits", XStr.encB64(ids), 30);
		}

		// Lấy danh sách mặt hàng đã xem
		List<Integer> list = new ArrayList<Integer>();
		for (String itemId : ids.substring(1).split(",")) {
			list.add(Integer.parseInt(itemId));
		}
		List<Product> visits = dao.findByIds(list);

		// bỏ dấu phẩy
		// Arrays.as = ids.substring(1).split(",");
		model.addAttribute("visits", visits);
		model.addAttribute("prod", p);
		return "product/detail";
	}

	@ResponseBody
	@RequestMapping("/product/share/{id}")
	public void share(@PathVariable("id") Integer id, MailInfo mail, HttpServletRequest req) {
		String link = req.getRequestURL().toString().replace("share", "detail");
		String body = mail.getContent() + "<hr/> Mời bạn vào click vào " + link + " để xem chi tiết sản phẩm";
		mailer.send(mail.getSender(), mail.getReceiver(), mail.getSubject(), body);
		
	}

	
	@RequestMapping("/product/like/{id}")
	public String like(@PathVariable("id") Integer id) {
		List<Integer> ids = cookie.getFavoriteIds();
		// Lấy danh sách mặt hàng đã xem
		if (!ids.remove(id)) {
			ids.add(id);
		}
		cookie.setFavoriteIds(ids);
		return "redirect:/layout/aside/favorite";
	}

}
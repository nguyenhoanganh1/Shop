package com.eshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeAController {
	@RequestMapping("/admin/home/index")
	public String index() {
		return "admin/report/index";
	}
}

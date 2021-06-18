package com.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class OTPController {

	@GetMapping("/otp_confirm")
	public String viewOTPLoginForm() {
		
		return "account/otp";
	}
}

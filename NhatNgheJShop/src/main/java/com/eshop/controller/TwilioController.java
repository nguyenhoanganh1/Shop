package com.eshop.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.bean.SmsRequest;
import com.eshop.service.SmsService;

@Controller
public class TwilioController {
	
	@Autowired
	private SmsService smsService;
	
	@RequestMapping("/account/twilioPage")
	public String twilioPage(Model model) {
		model.addAttribute("form", new SmsRequest());
		return "admin/customer/sent_message";
	}
	
	@PostMapping("/account/sent-sms")
	public String twilioSendSms(@ModelAttribute("form") SmsRequest smsReq) throws UnsupportedEncodingException {		
		String status = smsService.sendSms(smsReq);
		if("sent".equals(status) || "queued".equals(status)) {
			return "admin/customer/sent_message?message=" + URLEncoder.encode("Tin nhắn đã gửi thàn công","utf-8");
		}
		return "redirect:/admin/customer/sent_message?message=" + URLEncoder.encode("Vui lòng gửi lại","utf-8");
	}
	
}

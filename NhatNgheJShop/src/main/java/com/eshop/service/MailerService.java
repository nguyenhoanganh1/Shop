package com.eshop.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailerService {
	@Autowired
	JavaMailSender sender;

//	public void send(String to, String subject, String body, String...others) {
//		String from = "Java Shop <jshop@gmail.com>";
//		this.send(from, to, subject, body, others);
//	}
	
	
	
	public void send(String from, String to, String subject, String body, String...others) {
		try {
			MimeMessage mail = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");			
			helper.setFrom(from);	
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setReplyTo(from);
			
			String cc = this.get(CC, others);
			if(cc.length() > 0) {
				helper.setCc(cc.split("[,; ]+"));
			}
			
			String bcc = this.get(BCC, others);
			if(bcc.length() > 0) {
				helper.setBcc(bcc.split("[,; ]+"));
			}
			
			String files = this.get(ATTATCH, others);
			if(files.length() > 0) {
				for(String path: files.split("[,;]+")) {
					File file = new File(path.trim());
					helper.addAttachment(file.getName(), file);
				}
			}
			
			sender.send(mail);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private final int CC=0, BCC=1, ATTATCH=2;
	private String get(int key, String...others) {
		if(others.length > key && others[key] != null && others[key].trim().length() > 0) {
			return others[key].trim();
		}
		return "";
	}
}

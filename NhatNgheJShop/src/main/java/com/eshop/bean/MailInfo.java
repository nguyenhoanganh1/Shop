package com.eshop.bean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MailInfo {
	Integer id;
	String sender;
	String receiver;
	String subject;
	String content;
	
//	String from;
//	String to;
//	String subject;
//	String body;
//	String cc;
//	String bcc;
//	String attachs;
}

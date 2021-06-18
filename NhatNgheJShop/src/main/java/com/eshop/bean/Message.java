package com.eshop.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
	private String message;
	private String fromLogin;
	
	@Override
	public String toString() {
		return "Message{" + "Message: " + this.message + '\'' 
				+ ", fromLogin: " + this.fromLogin + '\'' + '}';
	}
	
	
	
}

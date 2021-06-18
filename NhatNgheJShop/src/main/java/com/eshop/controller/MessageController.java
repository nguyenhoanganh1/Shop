package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.bean.Message;
import com.eshop.bean.UserStorage;

@RestController
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, Message message) {
		System.out.println("handling send message = " + message +"to " + to);
		boolean isExists = UserStorage. getInstance().getUsers().contains(to);
		if(isExists) {
			simpleMessagingTemplate.convertAndSend("/topic/message/" + to, message);
		}
	}
	

}

package com.eshop.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.bean.UserStorage;

@CrossOrigin
@RestController
public class UserController {
	
	
	@GetMapping("/registration/{username}")
	public ResponseEntity<Void> register (@PathVariable("username") String userName){
		System.out.println("handling: " + userName);
		try {
			UserStorage.getInstance().setUsers(userName);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/fetchAllUsers")
	public Set<String> fetchAll(){
		return UserStorage.getInstance().getUsers();
	}
}

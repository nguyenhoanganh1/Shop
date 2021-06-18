package com.eshop.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static String setBCryptPasswordEncoder(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
		String encodePassword = passwordEncoder.encode(password);
		return encodePassword;
	}
	
	public static Boolean getBCryptPasswordEncoder(String userenteredpasswordWithotEncryoted, String encryptedPasswordFromDb)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
		boolean decodePassword = passwordEncoder.matches(userenteredpasswordWithotEncryoted, encryptedPasswordFromDb);
		return decodePassword;
	}
	
}

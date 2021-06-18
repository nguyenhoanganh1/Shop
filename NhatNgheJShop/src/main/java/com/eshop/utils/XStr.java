package com.eshop.utils;

import java.util.Base64;

public class XStr {
	public static String encB64(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}
	public static String decB64(String b64Text) {
		return new String(Base64.getDecoder().decode(b64Text));
	}
}

package com.eshop.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RecaptchaHandler {
	@Value("https://www.google.com/recaptcha/api/siteverify")
	private String url;

	@Value("6Ld99rUaAAAAAMyC7TJmjQn5Z85GU0gKU2ByrkDn")
	private String secretKey;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();		
	}
	
	@Autowired
	private RestTemplate template;
	
	HttpServletResponse resp;
	HttpServletRequest req;
	
	public boolean verifyReCAPTCHA(String gRecaptchaResponse) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKey);
		map.add("response", gRecaptchaResponse);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);
		
		RecaptchaResponse response =  template.postForObject(url, request, RecaptchaResponse.class);
		System.out.println(response.isSuccess());
		System.out.println(response.getHostname());
		System.out.println(response.getChallenge_ts());
		System.out.println(response.getErrorCodes());
		if(response.getErrorCodes() != null)
		{
			for (String error : response.getErrorCodes()) {
				System.out.println("\t" + error);
			}
		}
		return response.isSuccess();
	}
}

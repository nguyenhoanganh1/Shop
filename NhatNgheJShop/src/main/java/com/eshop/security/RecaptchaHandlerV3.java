package com.eshop.security;


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

public class RecaptchaHandlerV3 {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();		
	}
	
	@Autowired
	private RestTemplate template;
	@Value("https://www.google.com/recaptcha/api/siteverify")
	private String url;

	@Value("6LepSLYaAAAAAIjYRkwdFnqbmk-1Vzi4v2njkqa7")
	private String secretKey;
	
	
	public float verify(String recaptchaFormResponse) throws InvalidReCaptchaTokenException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKey);
		map.add("response", recaptchaFormResponse);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);
		
		RecaptchaResponseV3 response =  template.postForObject(url, request, RecaptchaResponseV3.class);
		System.out.println("isSuccess: "+response.isSuccess());
		System.out.println("getHostname: "+response.getHostname());
		System.out.println("getChallenge_ts: " + response.getChallenge_ts());
		System.out.println("getErrorCodes: " + response.getErrorCodes());
		System.out.println("getScore: "+response.getScore());
		System.out.println("recapcha Handler Called .. ..");
		System.out.println("g-recapcha response .. .." + recaptchaFormResponse);
		
		if(response.getErrorCodes() != null)
		{
			System.out.println("Error code: ");
			for (String error : response.getErrorCodes()) {
				System.out.println("\t" + error);
			}
		}
		if(!response.isSuccess())
		{
			throw new InvalidReCaptchaTokenException("Invalid ReCaptcha Token.");
		}
		return 0.4f;
		//return response.getScore();
	}
}

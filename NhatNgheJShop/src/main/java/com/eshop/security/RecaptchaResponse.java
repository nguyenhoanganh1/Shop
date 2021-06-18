package com.eshop.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaptchaResponse {
	
	private boolean success;
	private String  hostname;
	private String challenge_ts;
	
	@JsonProperty("error-codes")
	private String[] errorCodes; 
}

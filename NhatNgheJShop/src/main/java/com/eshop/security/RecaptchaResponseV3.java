package com.eshop.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaptchaResponseV3 {
	private boolean success;
	private String  hostname;
	private String challenge_ts;
	private float score;
	private String action;
	
	@JsonProperty("error-codes")
	private String[] errorCodes; 
}

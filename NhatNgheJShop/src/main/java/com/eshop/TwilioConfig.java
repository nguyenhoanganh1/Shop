package com.eshop;

import org.springframework.context.annotation.Configuration;

import com.eshop.service.TwilioService;
import com.twilio.Twilio;


@Configuration
public class TwilioConfig {
	private final TwilioService twiliService;
	
	public TwilioConfig(TwilioService twiliService) {
		this.twiliService = twiliService;
		Twilio.init(twiliService.getAccountSId(), twiliService.getAuthToken());
		System.out.println("twilio Initialized with account " + twiliService.getFromNumber());
	}
	
}

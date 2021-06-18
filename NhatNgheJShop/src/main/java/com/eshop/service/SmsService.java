package com.eshop.service;

import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.eshop.bean.SmsRequest;
import com.twilio.type.PhoneNumber;


@Service
public class SmsService {

	
	private final TwilioService twiliService;
	
	public SmsService(TwilioService twiliService) {
		this.twiliService = twiliService;	
	}
	
	public String sendSms(SmsRequest smsReq) {
		Message message = Message.creator(new PhoneNumber(smsReq.getNumber()), new PhoneNumber(twiliService.getFromNumber()), smsReq.getMessage()).create();
		return message.toString();
	}
}

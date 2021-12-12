package com.example.demo.service.user.twilio;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.models.user.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class SmsService {

	private final Twilioproperties twilioproperties;
	
	@Autowired
	public SmsService(Twilioproperties twilioproperties)
	{
		this.twilioproperties=twilioproperties;
	}
	
	//send message to number
	public String sendsms(SmsRequest smsrequest)
	{	Twilio.init(this.twilioproperties.getAccountSid(),this.twilioproperties.getAuthToken());
        //note: 1. if you want to send normal text sms remove string ("whatsapp:") in below message creator
		//      2. if you want to send whatsapp message "whatsapp:" string to be added before the numbers
		Message message=Message.creator(new PhoneNumber("whatsapp:"+smsrequest.getNumber()), new PhoneNumber("whatsapp:"+this.twilioproperties.getFromNumber()), smsrequest.getMessage()).create();
        return message.getStatus().toString();
        
	
	}
}

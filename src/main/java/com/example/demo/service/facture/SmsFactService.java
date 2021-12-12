package com.example.demo.service.facture;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public interface SmsFactService {
	void sendSMS(String msg);
}

    


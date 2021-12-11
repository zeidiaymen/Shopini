package com.example.demo.service.facture;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class SmsFactServiceIMPL implements SmsFactService{

	@Override
	public void sendSMS(String msg) {
		String ACCOUNT_SID="AC1773045f2e3445f825b6ed4570712ccb";
		String AUTH_TOKEN ="f290767b13b72401ce753df5196fea27";
		String FROM_NUMBER ="+13526463413";
	 
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message.creator(new PhoneNumber("+21654155657"), new PhoneNumber("+13526463413"), msg)
	            .create();
	    

    }

}

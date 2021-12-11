package com.example.demo.service.user.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class Twilioproperties {
	
	
	public Twilioproperties() {
		this.accountSid="ACb8cc84754109b164c644d54c60404a6c";
		this.authToken="320ce61dd1d6e026e9b124b5cf426232";
		this.fromNumber="+14155238886";
	}
	
	private String accountSid;
	private String authToken;
	private String fromNumber;
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	@Override
	public String toString() {
		return "Twilioproperties [accountSid=" + accountSid + ", authToken=" + authToken + ", fromNumber=" + fromNumber
				+ "]";
	}
	
	
	

}

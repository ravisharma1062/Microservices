package com.application.email.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;

import com.twilio.Twilio;

public abstract class TwilioService {
	
	@Value("${twilio.account.sid}")
    protected String sid;
	
	@Value("${twilio.auth.token}")
	protected String auth;
	
	@Value("${twilio.number}")
	protected String from;
	
	protected void sendMessage(String to, String text) throws URISyntaxException {
		Twilio.init(sid, auth);
	}
	
	protected void makeCall(String to) throws URISyntaxException {
		Twilio.init(sid, auth);
	}
	
}

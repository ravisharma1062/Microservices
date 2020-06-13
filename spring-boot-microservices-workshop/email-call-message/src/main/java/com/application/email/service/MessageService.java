package com.application.email.service;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageService extends TwilioService {
	
	public void sendMessage(String to, String text) throws URISyntaxException {
		
		super.sendMessage(to, text);
		
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(from), text).create();

        System.out.println(message.getBody());
        
	}
}

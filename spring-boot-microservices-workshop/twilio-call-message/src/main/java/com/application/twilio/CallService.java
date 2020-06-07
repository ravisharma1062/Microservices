package com.application.twilio;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@Service
public class CallService extends TwilioService{

	public void makeCall(String to) throws URISyntaxException {
		
		super.makeCall(to);

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from), new URI("http://demo.twilio.com/docs/voice.xml")).create();

        System.out.println(call.getCallerName());
        
	}
}

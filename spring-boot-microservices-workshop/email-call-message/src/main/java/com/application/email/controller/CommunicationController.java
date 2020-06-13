package com.application.email.controller;

import java.net.URISyntaxException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.email.dto.EmailDetails;
import com.application.email.service.CallService;
import com.application.email.service.EmailService;
import com.application.email.service.MessageService;

@RestController
public class CommunicationController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CallService callService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public String sendEmail(@RequestBody EmailDetails emailDetails) {
		try {
			return emailService.sendEmail(emailDetails);
		} catch (MessagingException e) {
			return "Send Email Failed";
		}
	}
	
	@RequestMapping("/makeCall/{toNumber}")
	public String makeCall(@PathVariable String toNumber) throws URISyntaxException {
		callService.makeCall(toNumber);
		
		return "Calling :" + toNumber;
	}
	
	@RequestMapping("/sendMessage/{toNumber}/{message}")
	public String sendMessage(@PathVariable String toNumber, @PathVariable String message) throws URISyntaxException {
		messageService.sendMessage(toNumber, message);
		
		return "Message - " + message + "- Sent to :" + toNumber;
	}
}

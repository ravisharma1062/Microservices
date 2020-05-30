package com.application.email;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public String sendEmail(@RequestBody EmailDetails emailDetails) {
		try {
			return emailService.sendEmail(emailDetails);
		} catch (MessagingException e) {
			return "Send Email Failed";
		}
	}
}

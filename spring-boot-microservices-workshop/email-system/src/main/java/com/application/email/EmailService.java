package com.application.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;

	public String sendEmail(EmailDetails emailDetails) throws MessagingException {

		for(String toAddress : emailDetails.getToAddress()) {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(toAddress);
			helper.setSubject(emailDetails.getSubject());
			helper.setText(mailContentBuilder.build(emailDetails.getBody()), true);

			if(emailDetails.getAttachmentPath() != null) {
				FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachmentPath()));
				helper.addAttachment("Test", file);
			}

			javaMailSender.send(message);
		}

		return "Email Sent";
	}
}

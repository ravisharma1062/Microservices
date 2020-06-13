package com.application.email.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.application.email.dto.EmailDetails;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	public String sendEmail(EmailDetails emailDetails) throws MessagingException {
		String[] emailList = new String[emailDetails.getToAddress().size()];
		int count = 0;
		for(String toAddress : emailDetails.getToAddress()) {
			emailList[count++] = toAddress;
		}
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(emailList);
		helper.setSubject(emailDetails.getSubject());
		helper.setText(mailContentBuilder.build(emailDetails.getBody()), true);

		if(emailDetails.getAttachmentPath() != null) {
			FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachmentPath()));
			helper.addAttachment("Test", file);
		}

		javaMailSender.send(message);


		return "Email Sent";
	}
}

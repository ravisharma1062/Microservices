package com.application.email;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmailDetails {
	private List<String> toAddress;
	private String fromAddress;
	private String subject;
	private String body;
	private String attachmentPath;
}

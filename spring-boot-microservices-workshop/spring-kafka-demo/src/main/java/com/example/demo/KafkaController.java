package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	
	@Autowired
	private Producer producer;
	
	@RequestMapping("/send/{message}")
	public void sendMessage(@PathVariable(value = "message") String message) {
		producer.sendMessage(message);
	}
}

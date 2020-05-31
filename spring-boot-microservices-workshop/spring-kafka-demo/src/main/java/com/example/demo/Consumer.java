package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	
	@KafkaListener(topics="email")
	public void consume(@Payload String message, @Headers MessageHeaders headers) {
		System.out.println(message);
		headers.keySet().forEach(key -> System.out.println(headers.get(key)));
	}

}

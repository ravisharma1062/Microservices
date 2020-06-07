package com.application.twilio;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@EnableEurekaClient
@SpringBootApplication 
public class TwilioCallMessageApplication {
	
	@Autowired
	private CallService callService;
	
	@Autowired
	private MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(TwilioCallMessageApplication.class, args);
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
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

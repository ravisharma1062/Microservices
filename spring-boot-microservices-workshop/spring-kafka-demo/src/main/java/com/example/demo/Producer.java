package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		try {
			ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("email", message);
			result.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onFailure(Throwable ex) {
					System.out.println("In Producer: " + ex);
					ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send("email", message);
				}
			});
		} catch (KafkaException e) {
			System.out.println("In Producer: " + e);
		}
	}
}

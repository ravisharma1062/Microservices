package io.javabrains.moviecatalogservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
	
    @Autowired
    private RestTemplate restTemplate;
	
//    @HystrixCommand(
//    		fallbackMethod = "getFallbackUserRating",
//    		threadPoolKey = "userRatingPool",
//    		threadPoolProperties = {
//    				@HystrixProperty(name = "coreSize", value = "20"),
//    				@HystrixProperty(name = "masQueueSize", value = "10")
//    		}
//    )
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data/ratingsdata/user/" + userId, UserRating.class);
	}
    
    @SuppressWarnings("unused")
	private UserRating getFallbackUserRating(String userId) {
    	List<Rating> ratingList = new ArrayList<>();
    	ratingList.add(new Rating("501", 2));
    	
    	UserRating rating = new UserRating();
    	rating.setUserId(userId);
    	rating.setRatings(ratingList);
    	return rating;
    }
}

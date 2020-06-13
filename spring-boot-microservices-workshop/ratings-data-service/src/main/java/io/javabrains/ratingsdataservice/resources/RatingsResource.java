package io.javabrains.ratingsdataservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.model.UserRating;
import io.javabrains.ratingsdataservice.repository.RatingsRepository;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	@Autowired
	private RatingsRepository userRatingMongoRepository;

    @RequestMapping(path="/user/{userId}", method = RequestMethod.GET)
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        return userRatingMongoRepository.findByUserId(userId);
    }
    
    @RequestMapping(path="/users", method = RequestMethod.GET)
    public List<UserRating> getAllUserRatings() {
        return userRatingMongoRepository.findAll();
    }
    
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public UserRating saveRating(@RequestBody UserRating userRating) {
        return userRatingMongoRepository.save(userRating);
    }

}

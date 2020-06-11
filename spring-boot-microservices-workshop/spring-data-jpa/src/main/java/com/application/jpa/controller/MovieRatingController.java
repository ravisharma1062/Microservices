package com.application.jpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.Movie;
import com.application.jpa.dto.MovieRating;
import com.application.jpa.dto.Users;
import com.application.jpa.service.MovieRatingsService;

@RestController
public class MovieRatingController {
		
	@Autowired
	MovieRatingsService movieRatingsService;
	    
    @RequestMapping(path = "/createrating/{userId}/{movieId}/{rating}", method = RequestMethod.POST)
    public MovieRating saveRating(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId, @PathVariable("rating") int rating) {
        return movieRatingsService.createRating(userId, movieId, rating);
    }
    
    @RequestMapping(path = "/createmovie", method = RequestMethod.POST)
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieRatingsService.saveMovie(movie);
    }
    
    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public Users saveUser(@RequestBody Users users) {
        return movieRatingsService.saveUser(users);
    }

}

package com.application.jpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.MovieDetails;
import com.application.jpa.dto.MovieRating;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class MovieRatingController {
		
	@Autowired
	MovieRatingsService movieRatingsService;
	    
	@ApiOperation(
			value = "createrating", 
			notes = "Save rating in DB", 
			httpMethod = "POST",
			response = MovieRating.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/createrating/{userId}/{movieId}/{rating}", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<MovieRating> saveRating(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId, @PathVariable("rating") int rating) throws DataJPAException {
    	MovieRating movieRating = movieRatingsService.createRating(userId, movieId, rating);
    	return new ResponseEntity<>(movieRating, HttpStatus.OK);
    }

}

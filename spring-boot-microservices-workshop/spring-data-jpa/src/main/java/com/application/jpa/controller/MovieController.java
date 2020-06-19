package com.application.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.MovieDetails;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "admin", description = "Admin Controller")
@RequestMapping("/admin")
public class MovieController {
	
	@Autowired
	MovieRatingsService movieRatingsService;
	
	@ApiOperation(
			value = "createmovie", 
			notes = "Save movie in DB", 
			httpMethod = "POST",
			response = MovieDetails.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/createmovie", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<?> saveMovie(@Valid @RequestBody MovieDetails movie, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	MovieDetails movieCreated = movieRatingsService.saveMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "updatemovie", 
			notes = "Updates a movie", 
			httpMethod = "PUT",
			response = MovieDetails.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/updatemovie", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody MovieDetails movie, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	MovieDetails movieCreated = movieRatingsService.updateMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "getMovie", 
			notes = "Get movie details", 
			httpMethod = "GET",
			response = MovieDetails.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET, consumes="application/json", produces="application/json")
    public ResponseEntity<MovieDetails> getMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getMovie(movieId), HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "deleteMovie", 
			notes = "delete movie", 
			httpMethod = "DELETE",
			response = String.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.DELETE, consumes="application/json", produces="application/json")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteMovie(movieId), HttpStatus.OK);
    }
}

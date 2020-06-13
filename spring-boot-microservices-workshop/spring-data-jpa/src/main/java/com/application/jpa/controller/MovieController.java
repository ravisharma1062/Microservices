package com.application.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.Movie;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

@RestController
public class MovieController {
	
	@Autowired
	MovieRatingsService movieRatingsService;
	
    
    @RequestMapping(path = "/createmovie", method = RequestMethod.POST)
    public ResponseEntity<?> saveMovie(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	Movie movieCreated = movieRatingsService.saveMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/updatemovie", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	Movie movieCreated = movieRatingsService.updateMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getMovie(movieId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteMovie(movieId), HttpStatus.OK);
    }
}

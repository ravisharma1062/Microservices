package com.application.jpa.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.Movie;
import com.application.jpa.dto.MovieRating;
import com.application.jpa.dto.Users;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

@RestController
public class MovieRatingController {
		
	@Autowired
	MovieRatingsService movieRatingsService;
	    
    @RequestMapping(path = "/createrating/{userId}/{movieId}/{rating}", method = RequestMethod.POST)
    public ResponseEntity<MovieRating> saveRating(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId, @PathVariable("rating") int rating) throws DataJPAException {
    	MovieRating movieRating = movieRatingsService.createRating(userId, movieId, rating);
    	return new ResponseEntity<>(movieRating, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/createmovie", method = RequestMethod.POST)
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {
    	Movie movieCreated = movieRatingsService.saveMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<Users> saveUser(@Valid @RequestBody Users users) {
    	Users usersCreated = movieRatingsService.saveUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }

    @RequestMapping(path = "/updatemovie", method = RequestMethod.PUT)
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
    	Movie movieCreated = movieRatingsService.updateMovie(movie);
    	return new ResponseEntity<>(movieCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/updateuser", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@Valid @RequestBody Users users) {
    	Users usersCreated = movieRatingsService.updateUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getUser(userId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getMovie(movieId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteUser(userId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") int movieId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteMovie(movieId), HttpStatus.OK);
    }
}

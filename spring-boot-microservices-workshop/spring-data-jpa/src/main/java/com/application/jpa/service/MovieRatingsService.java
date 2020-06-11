package com.application.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.application.jpa.dto.Movie;
import com.application.jpa.dto.MovieRating;
import com.application.jpa.dto.Users;
import com.application.jpa.repositories.MovieRepository;
import com.application.jpa.repositories.RatingsRepository;
import com.application.jpa.repositories.UserRepository;

@Service
public class MovieRatingsService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RatingsRepository ratingsRepository;
	
	@Transactional
	public MovieRating createRating(int userId, int movieId, int rating) {
		
		Movie movie = movieRepository.findByMovieId(movieId);
		Users users = userRepository.findByUserId(userId);
		
		MovieRating movieRating = new MovieRating(0, 4, movie, users);
		
		return ratingsRepository.save(movieRating);
	}
	
    @Transactional
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    
    @Transactional
    public Users saveUser(@RequestBody Users users) {
        return userRepository.save(users);
    }
}

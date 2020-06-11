package com.application.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dto.Movie;
import com.application.dto.MovieRating;
import com.application.dto.Users;
import com.application.repositories.MovieRepository;
import com.application.repositories.RatingsRepository;
import com.application.repositories.UserRepository;

@Service
public class MovieRatingsService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RatingsRepository ratingsRepository;
	
	public MovieRating createRating(int userId, int movieId, int rating) {
		
		Movie movie = movieRepository.findByMovieId(movieId);
		Users users = userRepository.findByUserId(userId);
		
		MovieRating movieRating = new MovieRating(0, 4, movie, users);
		
		return ratingsRepository.save(movieRating);
	}
}

package com.application.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.jpa.dto.MovieDetails;
import com.application.jpa.dto.MovieRating;
import com.application.jpa.dto.MovieUsers;
import com.application.jpa.exception.DataJPAException;
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
	public MovieRating createRating(int userId, int movieId, int rating) throws DataJPAException {
		
		MovieDetails movie = getMovieById(movieId);
		MovieUsers users = getUserById(userId);
		
		MovieRating movieRating = new MovieRating(0, 4, movie, users);
		
		return ratingsRepository.save(movieRating);
	}

	private MovieUsers getUserById(int userId) throws DataJPAException {
		return userRepository.findById(userId).orElseThrow(() -> new DataJPAException("INVALID_USER_ID", "Invalid User Id"));
	}

	private MovieDetails getMovieById(int movieId) throws DataJPAException {
		return movieRepository.findById(movieId).orElseThrow(() -> new DataJPAException("INVALID_MOVIE_ID", "Invalid Movie Id"));
	}
	
    @Transactional
    public MovieDetails saveMovie(MovieDetails movie) {
        return movieRepository.save(movie);
    }
    
    @Transactional
    public MovieUsers saveUser(MovieUsers users) {
        return userRepository.save(users);
    }
      
    @Transactional
    public MovieDetails updateMovie(MovieDetails movie) {
    	return movieRepository.findById(movie.getMovieId())
    		.map(movieFromDB -> {
    			movieFromDB.setMovieName(movie.getMovieName());
    			return movieRepository.save(movieFromDB);
    		})
    		.orElseGet(() -> {
    			return movieRepository.save(movie);
    		});
    }
    
    @Transactional
    public MovieUsers updateUser(MovieUsers users) {
    	return userRepository.findById(users.getUserId())
        		.map(userFromDB -> {
        			userFromDB.setUserName(users.getUserName());
        			userFromDB.setEmailId(users.getEmailId());
        			userFromDB.setPhoneNumber(users.getPhoneNumber());
        			return userRepository.save(userFromDB);
        		})
        		.orElseGet(() -> {
        			return userRepository.save(users);
        		});
    }
    
    public MovieUsers getUser(int userId) throws DataJPAException {
        return getUserById(userId);
    }
    
    public MovieDetails getMovie(int movieId) throws DataJPAException {
        return getMovieById(movieId);
    }
    
    @Transactional
    public String deleteUser(int userId) throws DataJPAException {
    	userRepository.deleteById(userId);
    	return "User Deleted";
    }
    
    @Transactional
    public String deleteMovie(int movieId) throws DataJPAException {
        movieRepository.deleteById(movieId);
        return "Movie Deleted";
    }
}

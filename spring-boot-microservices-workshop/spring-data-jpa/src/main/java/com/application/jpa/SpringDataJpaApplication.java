package com.application.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.Movie;
import com.application.dto.MovieRating;
import com.application.dto.Users;
import com.application.repositories.MovieRepository;
import com.application.repositories.UserRepository;

@RefreshScope
@EnableJpaRepositories(basePackages= {"com.application.repositories"})
@EntityScan("com.application.dto")
@EnableEurekaClient
@RestController
@SpringBootApplication
public class SpringDataJpaApplication {
	
	@Autowired
	MovieRatingsService movieRatingsService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
    
    @RequestMapping(path = "/createrating/{userId}/{movieId}/{rating}", method = RequestMethod.POST)
    public MovieRating saveRating(@PathVariable("userId") int userId, @PathVariable("movieId") int movieId, @PathVariable("rating") int rating) {
        return movieRatingsService.createRating(userId, movieId, rating);
    }
    
    @RequestMapping(path = "/createmovie", method = RequestMethod.POST)
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    
    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public Users saveUser(@RequestBody Users users) {
        return userRepository.save(users);
    }

}

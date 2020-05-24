package io.javabrains.ratingsdataservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.javabrains.ratingsdataservice.model.UserRating;

public interface RatingsRepository extends MongoRepository<UserRating, String>{
	UserRating findByUserId(String id);
}

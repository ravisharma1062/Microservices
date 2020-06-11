package io.javabrains.ratingsdataservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.javabrains.ratingsdataservice.model.UserRating;

@Repository
public interface RatingsRepository extends MongoRepository<UserRating, String>{
	UserRating findByUserId(String id);
}

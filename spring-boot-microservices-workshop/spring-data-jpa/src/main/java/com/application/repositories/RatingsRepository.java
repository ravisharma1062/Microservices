package com.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.dto.MovieRating;

@Repository
public interface RatingsRepository extends JpaRepository<MovieRating, Integer>{
	MovieRating findByRatingId(int id);
}

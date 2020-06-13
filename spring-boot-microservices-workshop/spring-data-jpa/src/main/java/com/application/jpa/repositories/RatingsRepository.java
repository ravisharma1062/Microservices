package com.application.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.jpa.dto.MovieRating;

@Repository
public interface RatingsRepository extends JpaRepository<MovieRating, Integer>{

}

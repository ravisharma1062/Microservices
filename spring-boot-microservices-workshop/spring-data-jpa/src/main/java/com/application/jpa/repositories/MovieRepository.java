package com.application.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.jpa.dto.MovieDetails;

@Repository
public interface MovieRepository extends JpaRepository<MovieDetails, Integer>{
	MovieDetails findByMovieId(int id);
}

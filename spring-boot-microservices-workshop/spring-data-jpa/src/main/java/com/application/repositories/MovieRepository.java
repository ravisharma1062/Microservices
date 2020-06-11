package com.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	Movie findByMovieId(int id);
}

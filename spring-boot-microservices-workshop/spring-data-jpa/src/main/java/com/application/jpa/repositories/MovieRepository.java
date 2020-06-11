package com.application.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.jpa.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	Movie findByMovieId(int id);
}

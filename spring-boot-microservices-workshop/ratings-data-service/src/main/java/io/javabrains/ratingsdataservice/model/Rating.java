package io.javabrains.ratingsdataservice.model;

import org.springframework.data.annotation.Id;

public class Rating {

	@Id
    private String movieId;
    private int rating;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

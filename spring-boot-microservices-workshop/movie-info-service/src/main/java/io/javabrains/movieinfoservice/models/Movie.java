package io.javabrains.movieinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	
    private String movieId;
    private String name;
    private String description;

}

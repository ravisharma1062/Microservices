package io.javabrains.ratingsdataservice.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rating {
	@Id
    private String movieId;
    private int rating;
}

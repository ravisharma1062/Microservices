package io.javabrains.ratingsdataservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value="User")
@Getter
@Setter
@NoArgsConstructor
public class UserRating {
	@Id
    private String userId;
    private List<Rating> ratings;
}

package io.javabrains.movieinfoservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MovieSummary {

    private String id;
    private String title;
    private String overview;

}

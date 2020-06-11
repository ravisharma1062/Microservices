package com.application.jpa.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Movie_Rating")
public class MovieRating {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Rating_Id")
	private int ratingId;
	@NotNull(message="Rating Cannot be blank")
	@Column(name="Rating")
	private int rating;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Movie_Id")
	private Movie movieId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_Id")
	private Users userId;
}

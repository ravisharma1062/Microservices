package com.application.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Movie")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Movie_Id")
	private int movieId;
	@Column(name="Movie_Name")
	private String movieName;
}

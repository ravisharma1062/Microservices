package com.application.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Movie_Users")
public class MovieUsers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_Id")
	private int userId;
	@NotBlank(message="User Name Cannot Be Blank")
	@Column(name="User_Name")
	private String userName;
	@Email(message="Invalid Email Address")
	@Pattern(regexp=".+@.+\\..+", message="Invalid Email Address")
	@Column(name="Email_Id", unique=true)
	private String emailId;
	@Pattern(regexp="(^$|[0-9]{10})", message="Invalid Phone Number")
	@Column(name="Phone_Number", unique=true)
	private String phoneNumber;
}

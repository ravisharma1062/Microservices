package com.application.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Login_Details")
public class LoginDetails {
	@Id
	@Column(name="Login_Id")
	@NotBlank(message="Login Id Cannot Be Blank")
	private String loginId;
	@Column(name="Password")
	@NotBlank(message="Password Cannot Be Blank")
	private String password;
	@Column(name="Role")
	@DefaultValue("ROLE_USER")
	private String role;

}

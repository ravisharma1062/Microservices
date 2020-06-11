package com.application.dto;

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
@Table(name="Users")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_Id")
	private int userId;
	@Column(name="User_Name")
	private String userName;
}

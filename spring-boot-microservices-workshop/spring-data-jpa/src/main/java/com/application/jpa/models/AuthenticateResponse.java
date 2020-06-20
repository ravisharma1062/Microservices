package com.application.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticateResponse {
	private final String jwt;
}

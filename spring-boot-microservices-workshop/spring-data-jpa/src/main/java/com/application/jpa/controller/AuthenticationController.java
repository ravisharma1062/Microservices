package com.application.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.configuration.JwtUtil;
import com.application.jpa.configuration.MyUserDetailsService;
import com.application.jpa.dto.MovieDetails;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.models.AuthenticateRequest;
import com.application.jpa.models.AuthenticateResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "AuthenticationController", description = "Authentication Controller")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@ApiOperation(
			value = "authenticate", 
			notes = "Creates JWT Token", 
			httpMethod = "POST",
			response = AuthenticateResponse.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest authenticateRequest) throws DataJPAException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new DataJPAException("INVALID_CREDENTIALS", "Invalid Username or Password");
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticateRequest.getUserName());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(new AuthenticateResponse(jwt), HttpStatus.OK);
		
	}
}

package com.application.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.MovieUsers;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MovieRatingsService movieRatingsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ApiOperation(
			value = "createuser", 
			notes = "Save user in DB", 
			httpMethod = "POST",
			response = MovieUsers.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/createuser", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public ResponseEntity<?> saveUser(@Valid @RequestBody MovieUsers users, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	users.getLoginDetails().setPassword(passwordEncoder.encode(users.getLoginDetails().getPassword()));
    	MovieUsers usersCreated = movieRatingsService.saveUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "updateuser", 
			notes = "Updates user in DB", 
			httpMethod = "PUT",
			response = MovieUsers.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/updateuser", method = RequestMethod.PUT, consumes="application/json", produces="application/json")
    public ResponseEntity<?> updateUser(@Valid @RequestBody MovieUsers users, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	users.getLoginDetails().setPassword(passwordEncoder.encode(users.getLoginDetails().getPassword()));
    	MovieUsers usersCreated = movieRatingsService.updateUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "get user", 
			notes = "get user details", 
			httpMethod = "GET",
			response = MovieUsers.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET, consumes="application/json", produces="application/json")
    public ResponseEntity<MovieUsers> getUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getUser(userId), HttpStatus.OK);
    }
    
	@ApiOperation(
			value = "delete user", 
			notes = "delete user details", 
			httpMethod = "DELETE",
			response = MovieUsers.class,
			consumes = "application/json",
			produces = "application/json"
	)
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.DELETE, consumes="application/json", produces="application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteUser(userId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public ResponseEntity<String> getHi() throws DataJPAException {
        return new ResponseEntity<>("Hi All!!", HttpStatus.OK);
    }

}

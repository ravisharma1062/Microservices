package com.application.jpa.controller;

import javax.validation.Valid;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.jpa.dto.MovieUsers;
import com.application.jpa.exception.DataJPAException;
import com.application.jpa.service.MovieRatingsService;

@RestController
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	MovieRatingsService movieRatingsService;
	
    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody MovieUsers users, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	MovieUsers usersCreated = movieRatingsService.saveUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/updateuser", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody MovieUsers users, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return ControllerUtil.getFieldErrorResponse("FieldError", ControllerUtil.getErrorResponseMap(bindingResult));
    	}
    	MovieUsers usersCreated = movieRatingsService.updateUser(users);
        return new ResponseEntity<>(usersCreated, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<MovieUsers> getUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.getUser(userId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) throws DataJPAException {
        return new ResponseEntity<>(movieRatingsService.deleteUser(userId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public ResponseEntity<String> getHi() throws DataJPAException {
        return new ResponseEntity<>("Hi All!!", HttpStatus.OK);
    }

}

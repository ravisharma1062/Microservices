package com.application.jpa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.application.jpa.dto.ErrorResponse;
import com.application.jpa.exception.DataJPAException;

@RestControllerAdvice
public class MovieRatingGlobalAdvice {
	
	@ExceptionHandler(DataJPAException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse  handleGlobleExceptions(DataJPAException dataJPAException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(dataJPAException.getErrorMessage());
		return errorResponse;
	}

}

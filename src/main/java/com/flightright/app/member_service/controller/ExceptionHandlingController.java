package com.flightright.app.member_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightright.app.member_service.entity.ExceptionResponse;
import com.flightright.app.member_service.exception.MemberNotFoundException;
import com.flightright.app.member_service.exception.MemberValidationException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(MemberValidationException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MemberValidationException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Validation Error");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ExceptionResponse> memberNotFound(MemberNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
}

package com.training.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionManager {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException() {
		System.out.println("I am from ControllerAdvice");
		ErrorResponse error = new ErrorResponse();
		error.setCode("404");
		error.setReason("Resource Not Found");
		error.setStatus("404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ClientErrorException.class)
	public ResponseEntity<ErrorResponse> handleClientErrorException(ClientErrorException ex) {
		System.out.println("I am from ControllerAdvice");
		ErrorResponse error = new ErrorResponse();
		error.setCode("404");
		error.setReason(ex.getMessage());
		error.setStatus("404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		System.out.println("I am from ControllerAdvice");
		ErrorResponse error = new ErrorResponse();
		error.setCode("404");
		error.setReason(ex.getMessage());
		error.setStatus("404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		System.out.println("I am from ControllerAdvice");
		ErrorResponse error = new ErrorResponse();
		error.setCode("400");
		error.setReason("Enter mandotary values");
		List<String> errorMessages = new ArrayList<>();
		
		List<ObjectError> errors = ex.getAllErrors();
		for(ObjectError e:errors) {
			errorMessages.add(e.getDefaultMessage());
		}
		error.setErrorMessages(errorMessages);
		error.setStatus("400");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		System.out.println("I am from ControllerAdvice");
		ErrorResponse error = new ErrorResponse();
		error.setCode("404");
		error.setReason(ex.getMessage());
		error.setStatus("404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}


}

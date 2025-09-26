package com.jsp.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.employee.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler  {
	
//	@ExceptionHandler(DoesNotExistException.class)
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleDoesNotExistException(DoesNotExistException doesNotExistException) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>(HttpStatus.NOT_FOUND.value(), "something went wrong", doesNotExistException.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
}
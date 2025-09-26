package com.jsp.employee.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoesNotExistException extends RuntimeException{
	private String message;
}
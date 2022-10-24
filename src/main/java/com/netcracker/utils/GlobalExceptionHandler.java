package com.netcracker.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netcracker.payload.Response;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class) 
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("in global exc handler : run time exc");
		return Response.error(e.getMessage());
	}
}

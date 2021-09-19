package com.nagaro.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccountStatementNotFoundException.class)
	public ResponseEntity<ExceptionResponse> exception(final AccountStatementNotFoundException exception,
			final HttpServletRequest request) {
		logger.error("Account  Statement Not Found Exception: ", exception.getMessage());
		
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception,
			final HttpServletRequest request) {
		logger.error("Invalid Input Exception: ", exception.getMessage());
		
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}

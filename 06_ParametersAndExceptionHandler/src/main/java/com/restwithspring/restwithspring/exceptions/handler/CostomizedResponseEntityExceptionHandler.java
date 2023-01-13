package com.restwithspring.restwithspring.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.restwithspring.restwithspring.exceptions.ExceptionResponse;
import com.restwithspring.restwithspring.exceptions.UnsupportedMathOperationException;

@ControllerAdvice //serve para concentrar todos os tratamentos dos controllers do projeto
@RestController
public class CostomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{ 

	@ExceptionHandler(Exception.class) ////será utilizado em erros mais genéricos, como 500
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			Exception ex, WebRequest request) { //final é uma variável que só pode ser atribuida uma vez
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(UnsupportedMathOperationException.class) 
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
			Exception ex, WebRequest request) { //final é uma variável que só pode ser atribuida uma vez
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}

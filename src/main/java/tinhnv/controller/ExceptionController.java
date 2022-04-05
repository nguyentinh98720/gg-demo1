package tinhnv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tinhnv.exception.AccountNotFoundException;
import tinhnv.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> accountNotFound(AccountNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage(), "", null));
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorResponse> accessDenied(Exception exception) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Something wrong!", exception.getMessage() , null));
//	}
}

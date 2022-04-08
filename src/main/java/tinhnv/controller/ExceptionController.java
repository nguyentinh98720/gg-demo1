package tinhnv.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tinhnv.exception.AccountExistException;
import tinhnv.exception.AccountNotFoundException;
import tinhnv.exception.FormTransferException;
import tinhnv.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> accountNotFound(AccountNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ErrorResponse(exception.getMessage(), "", null)
				);
	}
	
	@ExceptionHandler(AccountExistException.class)
	public ResponseEntity<ErrorResponse> accountExist(AccountExistException exception) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
				new ErrorResponse(exception.getMessage(), "", null)
				);
	}
	
	@ExceptionHandler(FormTransferException.class)
	public ResponseEntity<ErrorResponse> formError(FormTransferException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(exception.getMessage(), "", null));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> entityNotFound(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(exception.getMessage(), "", null));
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorResponse> accessDenied(Exception exception) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.body(new ErrorResponse("Something wrong!", exception.getMessage() , null));
//	}
}

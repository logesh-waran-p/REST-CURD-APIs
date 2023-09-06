package com.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentRestExceptionHandler {

	//add exception handler
	// getstudentByid method throw the exception the exceptionhandler return
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

			// create studenterrorresponse
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());

			// return responseEntity
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

		}
		
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleExceptions(Exception exc){
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage("not a correct format");
			error.setTimeStamp(System.currentTimeMillis());

			// return responseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
}

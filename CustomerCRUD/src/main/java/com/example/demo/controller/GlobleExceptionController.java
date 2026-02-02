package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.exception.InvalidName;
import com.example.demo.exception.InvalidTotal;

@ControllerAdvice

public class GlobleExceptionController {
	@ExceptionHandler(InvalidMobileNumber.class)
	public ResponseEntity<?> invalidMobileNumber(InvalidMobileNumber e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}

	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalidId(InvalidId e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());

	}

	@ExceptionHandler(InvalidName.class)
	public ResponseEntity<?> invalidName(InvalidName e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());

	}

	@ExceptionHandler(InvalidTotal.class)
	public ResponseEntity<?> invalidTottal(InvalidTotal e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}

}

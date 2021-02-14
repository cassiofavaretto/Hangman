package com.project.hangman.exception.handle;

import static com.project.hangman.constants.StatusCode.BAD_REQUEST;
import static com.project.hangman.constants.StatusCode.UNEXPECTED_ERROR;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.hangman.domain.ErrorResponse;
import com.project.hangman.exception.GameNotStartedException;
import com.project.hangman.exception.InvalidInputException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(GameNotStartedException.class)
	public ResponseEntity<ErrorResponse> gameNotStartedExceptionHandle(GameNotStartedException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(BAD_REQUEST.getCode());
		response.setMessage(e.getMessage());
		log.error("Error - [GameNotStartedException]: {}.", ExceptionUtils.getMessage(e));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> invalidInputExceptionHandle(InvalidInputException e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(BAD_REQUEST.getCode());
		response.setMessage(e.getMessage());
		log.error("Error - [InvalidInputException]: {}.", ExceptionUtils.getMessage(e));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandle(Exception e) {
		ErrorResponse response = new ErrorResponse();
		response.setStatus(UNEXPECTED_ERROR.getCode());
		response.setMessage(e.getMessage());
		log.error("Error - [Exception]: {}.", ExceptionUtils.getStackTrace(e));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}

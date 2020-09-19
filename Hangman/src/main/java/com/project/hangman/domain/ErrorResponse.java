package com.project.hangman.domain;

import lombok.Data;

@Data
public class ErrorResponse {

	private Integer status;
	private String message;
}

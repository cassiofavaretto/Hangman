package com.project.hangman.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

	BAD_REQUEST(400, "BAD_REQUEST"), 
	NOT_FOUND(404, "NOT_FOUND"), 
	UNEXPECTED_ERROR(500, "UNEXPECTED_ERROR");

	private Integer code;
	private String status;
}

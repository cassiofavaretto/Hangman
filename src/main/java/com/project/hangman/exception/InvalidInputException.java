package com.project.hangman.exception;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 9018286093475332083L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String msg) {
		super(msg);
	}
}

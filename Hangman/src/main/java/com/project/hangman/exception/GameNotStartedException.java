package com.project.hangman.exception;

public class GameNotStartedException extends Exception {

	private static final long serialVersionUID = -6027181166905140761L;

	public GameNotStartedException() {
		super();
	}

	public GameNotStartedException(String msg) {
		super(msg);
	}
}

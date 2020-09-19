package com.project.hangman.constants;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final Integer CHANCES = 6;
	public static final String STATUS_FAIL = "FAIL";
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_PLAYING = "PLAYING";
	public static final String MASK = "_";
}

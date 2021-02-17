package com.project.hangman.domain;

import java.util.List;

import lombok.Data;

@Data
public class Hangman {

	private String word;
	private List<String> choices;
	private Integer chancesRemaining;
	private String status;

}

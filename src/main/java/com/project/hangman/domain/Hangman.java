package com.project.hangman.domain;

import java.util.List;

import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@SessionScope
public class Hangman {

	private String word;
	private List<String> choices;
	private Integer chancesRemaining;
	private String status;

}

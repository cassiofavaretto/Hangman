package com.project.hangman.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HangmanWords {

	@JsonProperty("word_list")
	private List<String> words;
}

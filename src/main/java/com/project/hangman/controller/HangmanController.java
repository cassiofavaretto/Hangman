package com.project.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

import com.project.hangman.domain.Hangman;
import com.project.hangman.exception.GameNotStartedException;
import com.project.hangman.exception.InvalidInputException;
import com.project.hangman.service.HangmanService;

@Controller
@SessionScope
public class HangmanController {

	@Autowired
	private HangmanService hangmanService;

	@PostMapping("/start")
	public ResponseEntity<Hangman> startNewGame() {
		Hangman play = hangmanService.startNewGame();
		return ResponseEntity.status(HttpStatus.OK).body(play);
	}

	@PostMapping("/play/{letter}")
	public ResponseEntity<Hangman> playGame(@PathVariable String letter) throws GameNotStartedException, InvalidInputException {
		Hangman play = hangmanService.play(letter);
		return ResponseEntity.status(HttpStatus.OK).body(play);
	}

	@GetMapping("/session/message")
	public ResponseEntity<String> getMessage() {
		System.out.println(this);
		return ResponseEntity.status(HttpStatus.OK).body("Hello");
	}
}

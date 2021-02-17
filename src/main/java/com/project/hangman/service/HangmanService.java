package com.project.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hangman.constants.Constants;
import com.project.hangman.domain.Hangman;
import com.project.hangman.exception.GameNotStartedException;
import com.project.hangman.exception.InvalidInputException;
import com.project.hangman.utils.HangmanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HangmanService {

	@Autowired
	private HangmanUtils hangmanUtils;

	private List<String> words;

	private Hangman hangman;

	/**
	 * Select a new word and start the game
	 */
	public Hangman startNewGame() {
		log.info("startNewGame() - Starting a new game.");

		resetGame();

		log.info("startNewGame() - Started the game with word [{}] selected.", getHangman().getWord());

		return generatePlayResponse(getHangman());
	}

	/**
	 * Process the play. Validate the chances remaining and generate the response
	 * 
	 * @param letter
	 * @return the game response
	 * @throws GameNotStartedException
	 * @throws InvalidInputException
	 */
	public Hangman play(String letter) throws GameNotStartedException, InvalidInputException {
		if (!isGameStarted()) {
			throw new GameNotStartedException("Start the game before making a play.");
		}

		if (!isvalidLetter(letter)) {
			throw new InvalidInputException("You trying to use an invalid letter.");
		}

		letter = StringUtils.upperCase(letter);

		Integer chancesRemaining = getHangman().getChancesRemaining();
		log.info("play() - Chances Remaining [{}].", chancesRemaining);

		if (chancesRemaining < 1) {
			log.info("play() - No more chances remaining.");
			return generatePlayResponse(getHangman());
		}
		if (isGuessedWord()) {
			log.info("play() - No more chances remaining.");
			return generatePlayResponse(getHangman());
		}

		getHangman().getChoices().add(letter);

		log.info("play() - Playing with letter [{}].", letter);

		boolean contains = getHangman().getWord().contains(letter);

		if (!contains) {
			getHangman().setChancesRemaining(getHangman().getChancesRemaining() - 1);
		}

		return generatePlayResponse(getHangman());
	}

	/**
	 * Check if is a valid input
	 * 
	 * @param letter
	 */
	private boolean isvalidLetter(String letter) {
		return StringUtils.length(letter) == 1 && letter.chars().allMatch(Character::isLetter);
	}

	/**
	 * Check if the Hangman object is null
	 * 
	 */
	private boolean isGameStarted() {
		return getHangman().getWord() != null;
	}

	/**
	 * Generate the game response
	 * 
	 * @param hangman
	 */
	private Hangman generatePlayResponse(Hangman hangman) {
		Hangman response = new Hangman();
		response.setChancesRemaining(hangman.getChancesRemaining());
		response.setChoices(hangman.getChoices());
		response.setWord(hangmanUtils.getMaskedWord(hangman.getWord(), hangman.getChoices()));
		response.setStatus(getStatus(response));

		log.info("generatePlayResponse() - Returning response {}", response);

		return response;
	}

	/**
	 * Get the game status
	 * 
	 * @param response
	 * @return Playing, Success or Fail
	 */
	private String getStatus(Hangman hangman) {

		if (hangman.getChancesRemaining() < 1) {
			return Constants.STATUS_FAIL;
		}

		if (isGuessedWord()) {
			return Constants.STATUS_SUCCESS;
		}

		return Constants.STATUS_PLAYING;
	}

	/**
	 * Verify if the word is guessed
	 * 
	 */
	private boolean isGuessedWord() {
		String maskedWord = hangmanUtils.getMaskedWord(getHangman().getWord(), getHangman().getChoices());

		return !maskedWord.contains(Constants.MASK);
	}

	/**
	 * Reset the game properties
	 */
	private void resetGame() {
		getHangman().setWord(selectNewWord());
		getHangman().setChancesRemaining(Constants.CHANCES);
		getHangman().setChoices(new ArrayList<>());
	}

	/**
	 * Select a random word from words list
	 * 
	 */
	private String selectNewWord() {
		return getWordsList().get(new Random().nextInt(getWordsList().size()));
	}

	/**
	 * Get the list of possible words
	 * 
	 */
	public List<String> getWordsList() {
		if (this.words == null) {
			this.words = hangmanUtils.getWordsList();
		}
		return this.words;
	}

	/**
	 * Get the Hangman object
	 * 
	 */
	private Hangman getHangman() {
		if (this.hangman == null) {
			this.hangman = new Hangman();
		}

		return this.hangman;
	}
}

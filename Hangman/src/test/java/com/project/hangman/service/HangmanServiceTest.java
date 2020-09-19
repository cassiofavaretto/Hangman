package com.project.hangman.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.hangman.constants.Constants;
import com.project.hangman.domain.Hangman;
import com.project.hangman.exception.GameNotStartedException;
import com.project.hangman.exception.InvalidInputException;
import com.project.hangman.utils.HangmanUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HangmanService.class)
public class HangmanServiceTest {

	@Autowired
	private HangmanService hangmanService;

	@MockBean
	private HangmanUtils hangmanUtils;

	@Test
	public void startNewGame() {
		when(hangmanUtils.getWordsList()).thenReturn(getWordsList());
		when(hangmanUtils.getMaskedWord(Mockito.anyString(), Mockito.anyList())).thenReturn(getMaskedWord());

		Hangman game = hangmanService.startNewGame();

		assertNotNull(game.getWord());
		assertEquals(Constants.CHANCES, game.getChancesRemaining());
	}

	@Test
	public void wrongPlay() throws GameNotStartedException, InvalidInputException {
		when(hangmanUtils.getWordsList()).thenReturn(getWordsList());
		when(hangmanUtils.getMaskedWord(Mockito.anyString(), Mockito.anyList())).thenReturn(getMaskedWord());

		hangmanService.startNewGame();
		Hangman play = hangmanService.play("A");

		assertNotNull(play.getWord());
		assertEquals(Constants.CHANCES - 1, play.getChancesRemaining());
		assertThat(play.getChoices(), contains("A"));
	}

	@Test
	public void rightplay() throws GameNotStartedException, InvalidInputException {
		when(hangmanUtils.getWordsList()).thenReturn(getWordsList());
		when(hangmanUtils.getMaskedWord(Mockito.anyString(), Mockito.anyList())).thenReturn(getMaskedWord());

		hangmanService.startNewGame();
		Hangman play = hangmanService.play("D");

		assertNotNull(play.getWord());
		assertEquals(Constants.CHANCES, play.getChancesRemaining());
		assertThat(play.getChoices(), contains("D"));
	}

	@Test(expected = InvalidInputException.class)
	public void playInvalidInput() throws GameNotStartedException, InvalidInputException {
		when(hangmanUtils.getWordsList()).thenReturn(getWordsList());
		when(hangmanUtils.getMaskedWord(Mockito.anyString(), Mockito.anyList())).thenReturn(getMaskedWord());

		hangmanService.startNewGame();
		hangmanService.play("2");
	}

	private String getMaskedWord() {
		return "________";
	}

	private List<String> getWordsList() {
		List<String> words = new ArrayList<>();
		words.add("DELL");
		return words;
	}
}

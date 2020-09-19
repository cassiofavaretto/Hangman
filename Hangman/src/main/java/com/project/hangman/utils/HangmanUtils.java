package com.project.hangman.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.project.hangman.constants.Constants;
import com.project.hangman.domain.HangmanWords;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HangmanUtils {

	private ObjectMapper mapper;

	/**
	 * Mask the word to return to the user
	 * 
	 * @param word
	 * @param choises
	 */
	public String getMaskedWord(String word, List<String> choises) {
		String maskedWord = StringUtils.EMPTY;
		char[] charArray = word.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			String letter = String.valueOf(charArray[i]);
			if (choises.contains(letter)) {
				maskedWord = maskedWord.concat(letter);
			} else {
				maskedWord = maskedWord.concat(Constants.MASK);
			}
		}

		return maskedWord;
	}

	/**
	 * Get the list of words from the xml file
	 * 
	 */
	public List<String> getWordsList() {

		try {
			String xmlFileContent = getXMLFileContent("hangman");

			HangmanWords readValue = getObjectMapper().readValue(xmlFileContent, HangmanWords.class);

			return readValue.getWords();

		} catch (JsonProcessingException e) {
			log.error("getWordsList() - Error: {}", ExceptionUtils.getStackTrace(e));
		}

		return Collections.emptyList();
	}

	/**
	 * Get the xml file path
	 * 
	 * @param fileName
	 */
	private String getXMLFileContent(String fileName) {
		return readXMLFile("/xml/" + fileName + ".xml");
	}

	/**
	 * Read the xml file
	 * 
	 * @param filePath
	 */
	private String readXMLFile(String filePath) {
		try {
			final InputStream inputStream = new ClassPathResource(filePath).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			log.error("readXMLFile() - Error {}", ExceptionUtils.getStackTrace(e));
		}

		return StringUtils.EMPTY;
	}

	/**
	 * Create a new instance of ObjectMapper
	 * 
	 */
	private ObjectMapper getObjectMapper() {
		if (mapper == null) {
			mapper = new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}

		return mapper;
	}
}

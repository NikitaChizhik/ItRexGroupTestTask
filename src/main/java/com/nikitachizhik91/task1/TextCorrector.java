package com.nikitachizhik91.task1;

public class TextCorrector {

	public String removeC(String text) {
		if (text == null) {
			throw new IllegalArgumentException();
		}
		if (text.equals("")) {
			return "";
		}
		StringBuilder correctedText = new StringBuilder();
		String[] words = text.split(" ");
		for (String word : words) {
			word = replace(word);
			correctedText.append(word + " ");
		}
		return correctedText.toString().trim();
	}

	public String removeDoubleLetter(String text) {
		if (text == null) {
			throw new IllegalArgumentException();
		}
		if (text.equals("")) {
			return "";
		}
		// If text contains “ee” then replace it by simple “i”.
		text = text.replaceAll("ee", "i");
		// If “oo” then change it by “u”.
		text = text.replaceAll("oo", "u");
		// In other case any double letter should be changed by one letter.
		text = text.replaceAll("([A-Za-z])\\1", "$1");
		return text;
	}

	public String removeE(String text) {
		if (text == null) {
			throw new IllegalArgumentException();
		}
		if (text.equals("")) {
			return "";
		}
		StringBuilder correctedText = new StringBuilder();
		String[] words = text.split(" ");
		for (String word : words) {
			word = replaceE(word);
			correctedText.append(word + " ");
		}
		return correctedText.toString().trim();
	}

	private String replaceE(String wordArg) {
		StringBuilder word = new StringBuilder(wordArg);
		if (word.length() > 1 && word.charAt(word.length() - 1) == 'e') {
			word = word.deleteCharAt(word.length() - 1);
		}
		if (word.length() > 1 && word.charAt(word.length() - 2) == 'e' && word.charAt(word.length() - 1) == '!') {
			word = word.deleteCharAt(word.length() - 2);
		}
		if (word.length() > 1 && word.charAt(word.length() - 2) == 'e' && word.charAt(word.length() - 1) == '?') {
			word = word.deleteCharAt(word.length() - 2);
		}
		if (word.length() > 1 && word.charAt(word.length() - 2) == 'e' && word.charAt(word.length() - 1) == '.') {
			word = word.deleteCharAt(word.length() - 2);
		}
		if (word.length() > 1 && word.charAt(word.length() - 2) == 'e' && word.charAt(word.length() - 1) == ',') {
			word = word.deleteCharAt(word.length() - 2);
		}
		return word.toString();
	}

	public String removeArticles(String text) {
		if (text == null) {
			throw new IllegalArgumentException();
		}
		if (text.equals("")) {
			return "";
		}
		StringBuilder correctedText = new StringBuilder();
		String[] words = text.split(" ");
		for (String word : words) {
			if (word.equals("a") || word.equals("an") || word.equals("the")) {
				continue;
			}
			correctedText.append(word);
			correctedText.append(" ");
		}
		return correctedText.toString().trim();
	}

	private String replace(String word) {
		word = word.replaceAll("ci", "si");
		word = word.replaceAll("ce", "se");
		word = word.replaceAll("ck", "k");
		word = word.replaceAll("c", "k");
		return word;
	}

}

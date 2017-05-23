package com.nikitachizhik91.task1;

public class Main {
	public static void main(String[] args) {

		String text = "ci to si,ce to se,ck to k,c to k,ccc to kkk :)something like that";
		TextCorrector corrector = new TextCorrector();
		text = corrector.removeC(text);
		// System.out.println(text);

		text = "ee-i,oo-u,tt-t,dd-d.- aaa dt aaa";
		text = corrector.removeDoubleLetter(text);
		// System.out.println(text);

		text = "The - ThE , more - morE, rE!";
		text = corrector.removeE(text);
		// System.out.println(text);

		text = "a profound awareness of social injustice, an intense opposition to totalitarianism, "
				+ "a passion for clarity in language, and a belief in democratic socialism an ordinary text."
				+ "take the final test.an English articles expert.was an English author. the topic is very new.";
		text = corrector.removeArticles(text);
		System.out.println(text);
	}
}

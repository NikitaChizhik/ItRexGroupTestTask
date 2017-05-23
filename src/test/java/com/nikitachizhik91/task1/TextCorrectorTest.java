package com.nikitachizhik91.task1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextCorrectorTest {

	TextCorrector corrector = new TextCorrector();

	@Test(expected = IllegalArgumentException.class)
	public void removeC_ShouldThrowIllegalArgumentException() {
		corrector.removeC(null);
	}

	@Test
	public void removeC_EmptyString() {
		assertEquals("", corrector.removeC(""));
	}

	@Test
	public void removeC_SimpleText() {
		assertEquals("Suksess must be fair.si to si,se to se,k to k,k to k,kkk to kkk!",
				corrector.removeC("Success must be fair.ci to si,ce to se,ck to k,c to k,ccc to kkk!"));
	}

	@Test
	public void removeC_LargeText() {
		String expected = "I have stumbled on abbreviation k. in the legal kontext."
				+ " To be more presise, in the following sentense:1971 k. "
				+ "38 as modified by the Northern Ireland Akt 1998 (k. 47)."
				+ "What does this letter k stands for in this sentense? "
				+ "I thought of Clause, but I am not sure. Thank you all in advanse!";

		String text = "I have stumbled on abbreviation c. in the legal context. To be more precise, in the following sentence:"
				+ "1971 c. 38 as modified by the Northern Ireland Act 1998 (c. 47)."
				+ "What does this letter c stands for in this sentence? I thought of Clause, but I am not sure. "
				+ "Thank you all in advance!";
		assertEquals(expected, corrector.removeC(text));
	}

	@Test
	public void removeC_SpecificSymbols() {
		assertEquals("!@#$%^&*()_+{}\\/|k se si k", corrector.removeC("!@#$%^&*()_+{}\\/|ck ce ci c"));
	}

	@Test
	public void removeC_WithNumbers() {
		assertEquals("1234k 567se 89si 10k", corrector.removeC("1234ck 567ce 89ci 10c"));
	}

	@Test
	public void removeC_WithNonLatinLetters() {
		assertEquals("Не латинские буквы si se k k", corrector.removeC("Не латинские буквы ci ce ck c"));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test(expected = IllegalArgumentException.class)
	public void removeDoubleLetter_ShouldThrowIllegalArgumentException() {
		corrector.removeDoubleLetter(null);
	}

	@Test
	public void removeDoubleLetter_EmptyString() {
		assertEquals("", corrector.removeDoubleLetter(""));
	}

	@Test
	public void removeDoubleLetter_SimpleText() {
		assertEquals("This is just an expresion:i-i,u-u,t-t,d-d.- aa dt aa!",
				corrector.removeDoubleLetter("This is just an expression:ee-i,oo-u,tt-t,dd-d.- aaa dt aaa!"));
	}

	@Test
	public void removeDoubleLetter_LargeText() {
		String expected = "I have stumbled on abreviation c. in the legal context. "
				+ "To be more precise, in the folowing sentence:1971 c. 38 as modified by "
				+ "the Northern Ireland Act 1998 (c. 47).What does this leter c stands for in this sentence?"
				+ " I thought of Clause, but I am not sure. Thank you al in advance!";

		String text = "I have stumbled on abbreviation c. in the legal context."
				+ " To be more precise, in the following sentence:"
				+ "1971 c. 38 as modified by the Northern Ireland Act 1998 (c. 47)."
				+ "What does this letter c stands for in this sentence? I thought of Clause, but I am not sure. "
				+ "Thank you all in advance!";
		assertEquals(expected, corrector.removeDoubleLetter(text));
	}

	@Test
	public void removeDoubleLetter_SpecificSymbols() {
		assertEquals("!@#$%^&*()_+{}\\/|i-i,u-u,t-t", corrector.removeDoubleLetter("!@#$%^&*()_+{}\\/|ee-i,oo-u,tt-t"));
	}

	@Test
	public void removeDoubleLetter_WithNumbers() {
		assertEquals("1234i 567u 89t 10gg", corrector.removeDoubleLetter("1234ee 567oo 89tt 10gggg"));
	}

	@Test
	public void removeDoubleLetter_WithNonLatinLetters() {
		assertEquals("Не латинские буквы i u yy", corrector.removeDoubleLetter("Не латинские буквы ee oo yyyy"));
	}

	@Test
	public void removeDoubleLetter_ShouldNotRemoveDoubleDigits() {
		assertEquals("11-22-33-44-P", corrector.removeDoubleLetter("11-22-33-44-PP"));
	}

	@Test
	public void removeDoubleLetter_CaseSensitive() {
		assertEquals("aA-bB-E-O.", corrector.removeDoubleLetter("aA-bB-EE-OO."));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Test(expected = IllegalArgumentException.class)
	public void removeE_ShouldThrowIllegalArgumentException() {
		corrector.removeE(null);
	}

	@Test
	public void removeE_EmptyString() {
		assertEquals("", corrector.removeE(""));
	}

	@Test
	public void removeE_SimpleText() {
		assertEquals("Th sentenc : Th - Th , mor - mor, e - e , r! - r!",
				corrector.removeE("The sentence : The - Th , more - mor, e - e , re! - r!"));
	}

	@Test
	public void removeE_LargeText() {
		String expected = "To b mor precis, in th following sentenc : " + "What does this letter c stands for in this "
				+ "sentenc? I thought of Claus, but I am not sur. " + "Thank you all in advanc!";
		String text = " To be more precise, in the following sentence : "
				+ "What does this letter c stands for in this sentence? " + "I thought of Clause, but I am not sure. "
				+ "Thank you all in advance!";
		assertEquals(expected, corrector.removeE(text));
	}

	@Test
	public void removeE_SpecificSymbols() {
		assertEquals("!@#$%^&*()_+{}\\/|Th - Th , mor - mor, e - e , r! - r!",
				corrector.removeE("!@#$%^&*()_+{}\\/|The - Th , more - mor, e - e , re! - r!"));
	}

	@Test
	public void removeE_WithNumbers() {
		assertEquals("1234Th 567mor 10r!", corrector.removeE("1234The 567more 10re!"));
	}

	@Test
	public void removeE_WithNonLatinLetters() {
		assertEquals("Не латинские буквы Th mor r!", corrector.removeE("Не латинские буквы The more re!"));
	}

	@Test
	public void removeE_CaseSensitive() {
		assertEquals("Th - ThE , mor - morE, rE!", corrector.removeE("The - ThE , more - morE, rE!"));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test(expected = IllegalArgumentException.class)
	public void removeArticles_ShouldThrowIllegalArgumentException() {
		corrector.removeArticles(null);
	}

	@Test
	public void removeArticles_EmptyString() {
		assertEquals("", corrector.removeArticles(""));
	}

	@Test
	public void removeArticles_SimpleText() {
		assertEquals("table, pen, orange.", corrector.removeArticles("a table, the pen, an orange."));
	}

	@Test
	public void removeArticles_LargeText() {
		String expected = "profound awareness of social injustice, intense opposition to totalitarianism, "
				+ "passion for clarity in language, and belief in democratic socialism ordinary text."
				+ "take final test.an English articles expert.was English author. topic is very new.";
		String text = "a profound awareness of social injustice, an intense opposition to totalitarianism, "
				+ "a passion for clarity in language, and a belief in democratic socialism an ordinary text."
				+ "take the final test.an English articles expert.was an English author. the topic is very new.";
		assertEquals(expected, corrector.removeArticles(text));
	}

	@Test
	public void removeArticles_SpecificSymbols() {
		assertEquals("!@#$%^&*()_+{}\\/| table, pen, orange.",
				corrector.removeArticles("!@#$%^&*()_+{}\\/| a table, the pen, an orange."));
	}

	@Test
	public void removeArticles_WithNonLatinLetters() {
		assertEquals("Не латинские буквы table, pen, orange.",
				corrector.removeArticles("Не латинские буквы a table, the pen, an orange."));

	}

	@Test
	public void removeArticles_CaseSensitive() {
		assertEquals("A table,The pen, AN orange.", corrector.removeArticles("A table,The pen, AN orange."));
	}
}

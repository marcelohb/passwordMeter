package br.com.db1.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordTest {
	
	String text;
	
	@Test
	public void first() {
		text = "$";
		Password pass = new Password(text);
		assertEquals(4, pass.numberOfCaracters());
		assertEquals(0, pass.upperCaseLetters());
		assertEquals(0, pass.lowerCaseLetters());
		assertEquals(0, pass.numbers());
		assertEquals(6, pass.symbols());
		assertEquals(0, pass.middleNumbersOrSymbols());
		assertEquals(0, pass.requirements());
		assertEquals(0, pass.lettersOnly());
		assertEquals(0, pass.numbersOnly());
		assertEquals(0, pass.consecutiveUppercase());
		assertEquals(0, pass.consecutiveLowercase());
		assertEquals(0, pass.consecutiveNumbers());
		assertEquals(0, pass.sequentialLetters());
		assertEquals(0, pass.sequentialNumbers());
		assertEquals(0, pass.sequentialSymbols());
		assertEquals(10, pass.getScore());
		assertEquals(Complexity.VERY_WEAK.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityTooShort() {
		text = "";
		Password pass = new Password(text);
		assertEquals(0, pass.getScore());
		assertEquals(Complexity.TOO_SHORT.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityVeryWeak() {
		text = "2";
		Password pass = new Password(text);
		assertEquals(Complexity.VERY_WEAK.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityWeak() {
		text = "2cA";
		Password pass = new Password(text);
		assertEquals(12, pass.numberOfCaracters());
		assertEquals(4, pass.upperCaseLetters());
		assertEquals(4, pass.lowerCaseLetters());
		assertEquals(0, pass.lettersOnly());
		assertEquals(4, pass.numbers());
		assertEquals(24, pass.getScore());
		assertEquals(Complexity.WEAK.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityGood() {
		text = "2cAmmm0";
		Password pass = new Password(text);
		assertEquals(28, pass.numberOfCaracters());
		assertEquals(12, pass.upperCaseLetters());
		assertEquals(6, pass.lowerCaseLetters());
		assertEquals(8, pass.numbers());
		assertEquals(-4, pass.consecutiveLowercase());
		assertEquals(-4, pass.repeatCharacters());
		assertEquals(46, pass.getScore());
		assertEquals(Complexity.GOOD.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityStrong() {
		text = "meuName12";
		Password pass = new Password(text);
		assertEquals(36, pass.numberOfCaracters());
		assertEquals(16, pass.upperCaseLetters());
		assertEquals(6, pass.lowerCaseLetters());
		assertEquals(8, pass.numbers());
		assertEquals(0, pass.symbols());
		assertEquals(2, pass.middleNumbersOrSymbols());
		assertEquals(8, pass.requirements());
		assertEquals(-1, pass.repeatCharacters());
		assertEquals(-8, pass.consecutiveLowercase());
		assertEquals(-2, pass.consecutiveNumbers());
		assertEquals(65, pass.getScore());
		assertEquals(Complexity.STRONG.value(), pass.getComplexity());
	}
	
	@Test
	public void complexityVeryStrong() {
		text = "meuName12$";
		Password pass = new Password(text);
		assertEquals(40, pass.numberOfCaracters());
		assertEquals(18, pass.upperCaseLetters());
		assertEquals(8, pass.lowerCaseLetters());
		assertEquals(8, pass.numbers());
		assertEquals(6, pass.symbols());
		assertEquals(4, pass.middleNumbersOrSymbols());
		assertEquals(10, pass.requirements());
		assertEquals(-1, pass.repeatCharacters());
		assertEquals(-8, pass.consecutiveLowercase());
		assertEquals(-2, pass.consecutiveNumbers());
		assertEquals(83, pass.getScore());
		assertEquals(Complexity.VERY_STRONG.value(), pass.getComplexity());
	}

	@Test
	public void lengthZero() {
		text = "";
		Password pass = new Password(text);
		assertEquals(0, pass.getScore());
		assertEquals(Complexity.TOO_SHORT.value(), pass.getComplexity());
	}
	
	@Test
	public void maxIs100() {
		text = "123456789012345678901234567";
		Password pass = new Password(text);
		assertEquals(100, pass.getScore());
	}
	
	@Test
	public void numberOfCaracters() {
		text = "12345";
		Password pass = new Password(text);
		assertEquals(20, pass.numberOfCaracters());
	}
	
	@Test
	public void upperCaseLetters() {
		text = "123UUc";
		Password pass = new Password(text);
		assertEquals(8, pass.upperCaseLetters());
	}
	
	@Test
	public void loweCaseLetters() {
		text = "123UUc";
		Password pass = new Password(text);
		assertEquals(10, pass.lowerCaseLetters());
	}
	
	@Test
	public void numbers() {
		text = "123UUc";
		Password pass = new Password(text);
		assertEquals(12, pass.numbers());
	}
	
	@Test
	public void symbols() {
		text = "123$[]";
		Password pass = new Password(text);
		assertEquals(18, pass.symbols());
	}
	
	@Test
	public void middleNumbersOrSymbols() {
		text = "o1o#p";
		Password pass = new Password(text);
		assertEquals(4, pass.middleNumbersOrSymbols());
	}
	
	@Test
	public void requirements() {
		text = "meuSUP01";
		Password pass = new Password(text);
		assertEquals(8, pass.requirements());
	}
	
	@Test
	public void requirementsFail() {
		text = "meuSUP0";
		Password pass = new Password(text);
		assertEquals(0, pass.requirements());
	}
	
	@Test
	public void requirementsMax() {
		text = "meuSUP01!";
		Password pass = new Password(text);
		assertEquals(10, pass.requirements());
	}
	
	@Test
	public void lettersOnly() {
		text = "mmmMMmmm";
		Password pass = new Password(text);
		assertEquals(-8, pass.lettersOnly());
	}
	
	@Test
	public void noLettersOnlyWithNumbers() {
		text = "mmmMMmmm1";
		Password pass = new Password(text);
		assertEquals(0, pass.lettersOnly());
	}
	
	@Test
	public void noLettersOnlyWithSymbols() {
		text = "mmmMMmmm!";
		Password pass = new Password(text);
		assertEquals(0, pass.lettersOnly());
	}
	
	@Test
	public void numbersOnly() {
		text = "12345678";
		Password pass = new Password(text);
		assertEquals(-8, pass.numbersOnly());
	}
	
	@Test
	public void noNumbersOnlyWithUppers() {
		text = "A12345678A";
		Password pass = new Password(text);
		assertEquals(0, pass.numbersOnly());
	}
	
	@Test
	public void noNumbersOnlyWithLowers() {
		text = "a12345678a";
		Password pass = new Password(text);
		assertEquals(0, pass.numbersOnly());
	}
	
	@Test
	public void noNumbersOnlyWithSymbols() {
		text = "*12345678!";
		Password pass = new Password(text);
		assertEquals(0, pass.numbersOnly());
	}
	
	@Test
	public void noNumbersOnlyWithMix() {
		text = "A12345$78a";
		Password pass = new Password(text);
		assertEquals(0, pass.numbersOnly());
	}
	
	@Test
	public void repeatCharacters() {
		text = "1m1m$$";
		Password pass = new Password(text);
		assertEquals(-15, pass.repeatCharacters());
	}
	
	@Test
	public void noRepeatCharacters() {
		text = "12345";
		Password pass = new Password(text);
		assertEquals(0, pass.repeatCharacters());
	}
	
	@Test
	public void consecutiveUppercase() {
		text = "AAaAAAaAA";
		Password pass = new Password(text);
		assertEquals(-8, pass.consecutiveUppercase());
	}
	
	@Test
	public void noConsecutiveUppercase() {
		text = "aa000a11";
		Password pass = new Password(text);
		assertEquals(0, pass.consecutiveUppercase());
	}
	
	@Test
	public void consecutiveLowercase() {
		text = "aaAaaaAaa";
		Password pass = new Password(text);
		assertEquals(-8, pass.consecutiveLowercase());
	}
	
	@Test
	public void noConsecutiveLowercase() {
		text = "AA000A11";
		Password pass = new Password(text);
		assertEquals(0, pass.consecutiveLowercase());
	}
	
	@Test
	public void consecutiveNumbers() {
		text = "aa111bb22ss";
		Password pass = new Password(text);
		assertEquals(-6, pass.consecutiveNumbers());
	}
	
	@Test
	public void noConsecutiveNumbers() {
		text = "AAaaaA1$";
		Password pass = new Password(text);
		assertEquals(0, pass.consecutiveNumbers());
	}
	
	@Test
	public void sequenciaLetters() {
		text = "abCdE1deF0";
		Password pass = new Password(text);
		assertEquals(-12, pass.sequentialLetters());
	}
	
	@Test
	public void noSequenciaLetters() {
		text = "a1b1C0dE1dXeF0";
		Password pass = new Password(text);
		assertEquals(0, pass.sequentialLetters());
	}
	
	@Test
	public void sequenciaNumbers() {
		text = "123abC789dE1deF0";
		Password pass = new Password(text);
		assertEquals(-6, pass.sequentialNumbers());
	}
	
	@Test
	public void noSequenciaNumbers() {
		text = "a1b1C0dE1dXeF0";
		Password pass = new Password(text);
		assertEquals(0, pass.sequentialNumbers());
	}
	
	@Test
	public void sequentialSymbols() {
		text = ")!@#$";
		Password pass = new Password(text);
		assertEquals(-9, pass.sequentialSymbols());
	}
	
	@Test
	public void noSequentialSymbols() {
		text = "!s1@cb#0";
		Password pass = new Password(text);
		assertEquals(0, pass.sequentialSymbols());
	}	

}

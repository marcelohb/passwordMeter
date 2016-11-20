package br.com.db1.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordTest {
	
	String text;

	@Test
	public void lengthZero() {
		text = "";
		Password pass = new Password(text);
		assertEquals(0, pass.getScore());
		assertEquals(Complexity.TOO_SHORT, pass.getComplexity());
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
		assertEquals(10, pass.loweCaseLetters());
	}
	
	@Test
	public void numbers() {
		text = "123UUc";
		Password pass = new Password(text);
		assertEquals(12, pass.numbers());
	}
	
	@Test
	public void simbols() {
		text = "123$[]";
		Password pass = new Password(text);
		assertEquals(18, pass.simbols());
	}

}

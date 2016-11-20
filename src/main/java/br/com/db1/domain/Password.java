package br.com.db1.domain;

public class Password {
	
	private int score;
	private Complexity complexity;
	private String text;
	
	public Password(String text) {
		this.score = 0;
		this.complexity = Complexity.TOO_SHORT;
		this.text = text;
		validade();
	}

	private void validade() {
		numberOfCaracters();
	}

	public int getScore() {
		if (this.score > 100)
			return 100;
		return score;
	}
	
	public Complexity getComplexity() {
		return complexity;
	}

	public int numberOfCaracters() {
		int bonus = text.length() * 4;
		this.score += bonus;
		return bonus;
	}

	public int upperCaseLetters() {
		int uppers = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[A-Z]"))
				uppers ++;
		}
		int bonus = (text.length()-uppers)*2;
		this.score += bonus;
		return bonus;
	}

	public Object loweCaseLetters() {
		int lowers = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[a-z]"))
				lowers ++;
		}
		int bonus = (text.length()-lowers)*2;
		this.score += bonus;
		return bonus;
	}

	public Object numbers() {
		int numbers = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[0-9]"))
				numbers ++;
		}
		int bonus = numbers*4;
		this.score += bonus;
		return bonus;
	}

	public Object simbols() {
		int simbols = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[^0-9^A-Z^a-z]"))
				simbols ++;
		}
		int bonus = simbols*6;
		this.score += bonus;
		return bonus;
	}

}

package br.com.db1.domain;

public class Password {
	
	private int score;
	private Complexity complexity;
	private String text;
	private String symbols = ")!@#$%^&*()";
	
	public Password(String text) {
		this.score = 50;
		this.complexity = Complexity.TOO_SHORT;
		this.text = text;
		validade();
	}

	private void validade() {
		// ADDITIONS
		numberOfCaracters();
		upperCaseLetters();
		lowerCaseLetters();
		numbers();
		symbols();
		middleNumbersOrSymbols();
		requirements();
		// DEDUCTIONS
		lettersOnly();
		numbersOnly();
		//repeatedChars();
		consecutiveUppercase();
		consecutiveLowercase();
		consecutiveNumbers();
		sequentialLetters();
		sequentialNumbers();
		sequentialSymbols();
	}

	public int getScore() {
		if (this.score > 100)
			return 100;
		return score;
	}
	
	public String getComplexity() {
		return complexity.value();
	}
	
	public boolean minimunLength() {
		if (text.length() < 8)
			return false;
		return true;
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
		if (uppers == 0)
			return uppers;
		int bonus = (text.length()-uppers)*2;
		this.score += bonus;
		return bonus;
	}

	public int lowerCaseLetters() {
		int lowers = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[a-z]"))
				lowers ++;
		}
		if (lowers == 0)
			return lowers;
		int bonus = (text.length()-lowers)*2;
		this.score += bonus;
		return bonus;
	}

	public int numbers() {
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

	public int symbols() {
		int symbols = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[^0-9^A-Z^a-z]"))
				symbols ++;
		}
		int bonus = symbols*6;
		this.score += bonus;
		return bonus;
	}
	
	public int middleNumbersOrSymbols() {
		int mns = 0; 
		if (text.length() == 0)
			return 0;
		// TODO refatorar para criar um método que buscar a expressao informada
		char a[] = text.substring(1, text.length()-1).toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[^0-9^A-Z^a-z]"))
				mns ++;
			if (String.valueOf(c).matches("[0-9]"))
				mns ++;
		}
		int bonus = mns * 2;
		this.score += bonus; // TODO refatorar score para triar um metodo de atualizar
		return bonus;
	}

	public int requirements() {
		int requirements = 0;
		if (minimunLength())
			requirements ++;
		if (upperCaseLetters() > 0 && minimunLength())
			requirements ++;
		if (lowerCaseLetters() > 0 && minimunLength())
			requirements ++;
		if (numbers() > 0 && minimunLength())
			requirements ++;
		if (symbols() > 0 && minimunLength())
			requirements ++;
		int bonus = requirements * 2;
		return bonus;
	}

	public int lettersOnly() {
		int letters = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[A-Z-a-z]"))
				letters ++;
		}
		if (numbers() > 0 || symbols() > 0)
			letters = 0;
		int bonus = letters * -1;
		this.score += bonus;
		return bonus;
	}

	public int numbersOnly() {
		int numbers = 0;
		char a[] = text.toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[0-9]"))
				numbers ++;
		}
		if (upperCaseLetters() > 0 || lowerCaseLetters() > 0 || symbols() > 0)
			numbers = 0;
		int bonus = numbers * -1;
		this.score += bonus;
		return bonus;
	}

	public int consecutiveUppercase() {
		int uppers = 0;
		char a[] = text.toCharArray();
		char previous = 0;
		for (char c : a) {
			if (String.valueOf(c).matches("[A-Z]") && String.valueOf(previous).matches("[A-Z]"))
				uppers ++;
			previous = c;
		}
		int bonus = (uppers*2) * -1;
		this.score += bonus;
		return bonus;
	}

	public int consecutiveLowercase() {
		int lowers = 0;
		char a[] = text.toCharArray();
		char previous = 0;
		for (char c : a) {
			if (String.valueOf(c).matches("[a-z]") && String.valueOf(previous).matches("[a-z]"))
				lowers ++;
			previous = c;
		}
		int bonus = (lowers*2) * -1;
		this.score += bonus;
		return bonus;
	}

	public int consecutiveNumbers() {
		int numbers = 0;
		char a[] = text.toCharArray();
		char previous = 0;
		for (char c : a) {
			if (String.valueOf(c).matches("[0-9]") && String.valueOf(previous).matches("[0-9]"))
				numbers ++;
			previous = c;
		}
		int bonus = (numbers*2) * -1;
		this.score += bonus;
		return bonus;
	}

	public int sequentialLetters() {
		int sequential = 0;
		char a[] = text.toLowerCase().toCharArray();
		char previous = 0; char bPrevious = 0;
		for (char c : a) {
			if (Integer.valueOf(c-1).equals(Integer.valueOf(previous)) 
					&& Integer.valueOf(c-2).equals(Integer.valueOf(bPrevious)) 
					&& String.valueOf(c).matches("[a-z]"))
				sequential ++;
			bPrevious = previous;
			previous = c;
		}
		int bonus = (sequential*3) * -1;
		this.score += bonus;
		return bonus;
	}

	public int sequentialNumbers() {
		int sequential = 0;
		char a[] = text.toCharArray();
		char previous = 0; char bPrevious = 0;
		for (char c : a) {
			if (Integer.valueOf(c-1).equals(Integer.valueOf(previous)) 
					&& Integer.valueOf(c-2).equals(Integer.valueOf(bPrevious))
					&& String.valueOf(c).matches("[0-9]"))
				sequential ++;
			bPrevious = previous;
			previous = c;
		}
		int bonus = (sequential*3) * -1;
		this.score += bonus;
		return bonus;
	}

	public int sequentialSymbols() {
		int sequential = 0;
		char a[] = text.toCharArray();
		
		String subSequential = "";
		for (char c : a) {
			subSequential += c;
			if (subSequential.length() > 3)
				subSequential = subSequential.substring(1, 4);
			if (symbols.indexOf(subSequential) >= 0 && subSequential.length() == 3)
				sequential ++;
		}
		
		int bonus = (sequential*3) * -1;
		this.score += bonus;
		return bonus;
	}
	
	@Override
	public String toString() {
		return "{\"score\":"+getScore()+",\"complexity\":\""+ getComplexity()+"\"}";
	}

}

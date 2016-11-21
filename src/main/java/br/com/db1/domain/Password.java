package br.com.db1.domain;

public class Password {
	
	private int score;
	private Complexity complexity;
	private String text;
	private String symbols = ")!@#$%^&*()";
	
	public Password(String text) {
		this.score = 0;
		this.complexity = Complexity.TOO_SHORT;
		this.text = text;
		validade();
	}

	private void validade() {
		// ADDITIONS
		updateScore(numberOfCaracters());
		updateScore(upperCaseLetters());
		updateScore(lowerCaseLetters());
		updateScore(numbers());
		updateScore(symbols());
		updateScore(middleNumbersOrSymbols());
		updateScore(requirements());
		// DEDUCTIONS
		updateScore(lettersOnly());
		updateScore(numbersOnly());
		updateScore(repeatCharacters());
		updateScore(consecutiveUppercase());
		updateScore(consecutiveLowercase());
		updateScore(consecutiveNumbers());
		updateScore(sequentialLetters());
		updateScore(sequentialNumbers());
		updateScore(sequentialSymbols());
		// COMPLEXITY
		complexity();
	}

	public int getScore() {
		if (this.score > 100)
			return 100;
		return this.score;
	}
	
	public String getComplexity() {
		return complexity.value();
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateScore(int score) {
		this.score += score;
	}
	
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	
	public void complexity() {
		if (getScore() == 0)
			setComplexity(Complexity.TOO_SHORT);
		if (getScore() > 0 && getScore() < 20)
			setComplexity(Complexity.VERY_WEAK);
		if (getScore() >= 20 && getScore() < 40)
			setComplexity(Complexity.WEAK);
		if (getScore() >= 40 && getScore() < 60)
			setComplexity(Complexity.GOOD);
		if (getScore() >= 60 && getScore() < 80)
			setComplexity(Complexity.STRONG);
		if (getScore() >= 80)
			setComplexity(Complexity.VERY_STRONG);
	}
	
	public boolean minimunLength() {
		if (text.length() < 8)
			return false;
		return true;
	}

	public int numberOfCaracters() {
		int bonus = text.length() * 4;
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
		return bonus;
	}
	
	public int middleNumbersOrSymbols() {
		int mns = 0; 
		if (text.length() == 0)
			return 0;
		if (text.length() < 2)
			return 0;
		char a[] = text.substring(1, text.length()-1).toCharArray();
		for (char c : a) {
			if (String.valueOf(c).matches("[^0-9^A-Z^a-z]"))
				mns ++;
			if (String.valueOf(c).matches("[0-9]"))
				mns ++;
		}
		int bonus = mns * 2;
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
		return bonus;
	}

	public int repeatCharacters() {
		Double repeat = 0.0;
		int qtdRepeat = 0;
		int unique;
		int lengthA = text.length(), lengthB = text.length();
		int bonus = 0;
		boolean existChar;
		
		for (int i=0; i < lengthA; i++) {
			existChar = false;
			for (int t=0; t < lengthB; t++) {
				if (text.charAt(i) == text.charAt(t) && i != t) {
					existChar = true;
					repeat += Math.abs(lengthA/(t-i));
				}
			}
			if (existChar) {
				qtdRepeat ++;
				unique = lengthA-qtdRepeat;
				repeat = (unique > 0) ? Math.ceil(repeat/unique) : Math.ceil(repeat);
			}
		}
		bonus = repeat.intValue() * -1; 
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
		return bonus;
	}
	
	@Override
	public String toString() {
		return "{\"score\":"+getScore()+",\"complexity\":\""+ getComplexity()+"\"}";
	}

}

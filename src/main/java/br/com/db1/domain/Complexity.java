package br.com.db1.domain;

public enum Complexity {
	
	TOO_SHORT("Too Short"), 
	VERY_WEAK("Very Weak"),
	WEAK("Weak"),
	GOOD("Good"),
	STRONG("Strong"),
	VERY_STRONG("Very Stron");
	
	private String value;
	
	private Complexity(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}

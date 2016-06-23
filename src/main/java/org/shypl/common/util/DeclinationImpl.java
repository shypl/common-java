package org.shypl.common.util;

public class DeclinationImpl implements Declination {
	private static final long serialVersionUID = 42;

	private final String word1;
	private final String word2;
	private final String word5;

	public DeclinationImpl(String word1, String word2, String word5) {
		this.word1 = word1;
		this.word2 = word2;
		this.word5 = word5;
	}

	@Override
	public String getWord1() {
		return word1;
	}

	@Override
	public String getWord2() {
		return word2;
	}

	@Override
	public String getWord5() {
		return word5;
	}
}

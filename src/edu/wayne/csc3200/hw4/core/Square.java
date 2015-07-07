package edu.wayne.csc3200.hw4.core;

public abstract class Square {

	private boolean playerHasPlaced;
	private boolean containsLetter;

	private Letter containedLetter;
	Letter specialLetter;// special tiles
	private int squareBonus;
	private int letterBonus;
	private boolean containsSpecial;

	public static final char REG = 48;
	public static final char TWO_LETTER_BONUS = 49;
	public static final char THREE_LETTER_BONUS = 50;
	public static final char TWO_WORD_BONUS = 51;
	public static final char THREE_WORD_BONUS = 53;
	public static final char BOOM_TILE = 54;
	public static final char NEG_TILE = 55;
	public static final char REV_TILE = 56;
	public static final char SKIP_TILE = 57;

	public Square() {
		playerHasPlaced = false;
		containsLetter = false;
		containsSpecial = false;
	}

	public Square(Letter letter) {
		this.containedLetter = letter;
		containsLetter = true;
		playerHasPlaced = false;
		containsSpecial = false;
	}

	public abstract char getSquareType();

	public void placeUpdateSquare() {

		this.setContainsLetter(true);
		this.setPlayerHasPlaced(true);
	}

	public Letter getContainedLetter() {
		return containedLetter;
	}

	public char getContainedchar() {
		return containedLetter.getSymbol();
	}

	public int getSquareBonus() {
		return squareBonus;
	}

	public void setSquareBonus(int squareBonus) {
		this.squareBonus = squareBonus;
	}

	public void setContainedLetter(Letter containedLetter) {
		this.containedLetter = containedLetter;
		this.setContainsLetter(true);
	}

	public boolean isPlayerHasPlaced() {
		return playerHasPlaced;
	}

	public void setPlayerHasPlaced(boolean playerHasPlaced) {
		this.playerHasPlaced = playerHasPlaced;
	}

	public int getLetterBonus() {
		return letterBonus;
	}

	public void setLetterBonus(int letterBonus) {
		this.letterBonus = letterBonus;
	}

	public boolean containsLetter() {
		return containsLetter;
	}

	public void setContainsLetter(boolean containsLetter) {
		this.containsLetter = containsLetter;
	}

	public Letter getSpecialLetter() {
		return specialLetter;
	}

	public void setSpecialLetter(Letter specialLetter) {
		this.specialLetter = specialLetter;
	}

	public boolean isContainsSpecial() {
		return containsSpecial;
	}

	public void setContainsSpecial(boolean containsSpecial) {
		this.containsSpecial = containsSpecial;
	}

}

package edu.wayne.csc3200.hw4.core;

public class TripleLetterSquare extends Square {
	private int squareBonus = 3;
	private int letterBonus = 1;
	

	public TripleLetterSquare() {
		setPlayerHasPlaced(false);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
	}

	@Override
	public char getSquareType() {
		return THREE_LETTER_BONUS;
		
	}

}

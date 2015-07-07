package edu.wayne.csc3200.hw4.core;

public class DoubleLetterSquare extends Square{
	private int squareBonus = 1;
	private int letterBonus = 2;
	

	public DoubleLetterSquare() {
		setPlayerHasPlaced(false);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
	}

	@Override
	public char getSquareType() {
		return TWO_LETTER_BONUS;
		
	}
}

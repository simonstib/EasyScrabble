package edu.wayne.csc3200.hw4.core;

public class DoubleWordSquare extends Square {

	private int squareBonus = 2;
	private int letterBonus = 1;
	

	public DoubleWordSquare() {
		setPlayerHasPlaced(false);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
	}

	@Override
	public char getSquareType() {
		
		return TWO_WORD_BONUS;
	}

}

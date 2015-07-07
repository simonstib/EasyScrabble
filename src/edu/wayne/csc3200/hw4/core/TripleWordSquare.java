package edu.wayne.csc3200.hw4.core;

public class TripleWordSquare extends Square {

	private int squareBonus = 3;
	private int letterBonus = 1;
	

	public TripleWordSquare() {
		setPlayerHasPlaced(false);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
	}

	@Override
	public char getSquareType() {
		
		return THREE_WORD_BONUS;
	}

}

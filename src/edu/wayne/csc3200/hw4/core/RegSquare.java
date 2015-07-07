package edu.wayne.csc3200.hw4.core;

public class RegSquare extends Square {
	private int letterBonus = 1;
	private int squareBonus = 1;


	public RegSquare() {
		setPlayerHasPlaced(false);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
	
	
	}
	
	public RegSquare(Letter letter){
		super(letter);
		setSquareBonus(squareBonus);
		setLetterBonus(letterBonus);
		setPlayerHasPlaced(false);
		
		
	}

	@Override
	public char getSquareType() {
		return REG;
	}
}

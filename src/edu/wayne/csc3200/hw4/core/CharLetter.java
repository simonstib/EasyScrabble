package edu.wayne.csc3200.hw4.core;

public class CharLetter extends Letter {

	public CharLetter() {
	}

	public CharLetter(char symbol, int points) {
		super(symbol, points);
		setPrice(0);
	}

	

	@Override
	public void enactTileAction(Game game, int row, int col) {
		//intentionally left blank
		
	}

}

package edu.wayne.csc3200.hw4.core;

public class LoseScoreTile extends Letter {

	private int loseLetterCost = 30;
	private char loseLetterSymbol = 'L';

	public LoseScoreTile() {
		
		setPrice(loseLetterCost);
		setSymbol(loseLetterSymbol);
	}

	/**
	 * subtract from player score
	 * 
	 * @return score to be subtracted
	 */
	public void loseScore(Game game) {
		game.getCurrentPlayer().setPlayerScore(0);
	}

	@Override
	public void enactTileAction(Game game,int row, int col) {
		
		loseScore(game);
	}


}

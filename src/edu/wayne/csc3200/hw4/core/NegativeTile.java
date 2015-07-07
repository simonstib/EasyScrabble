package edu.wayne.csc3200.hw4.core;

public class NegativeTile extends Letter {

	private int negTileCost = 25;
	private char negSymbol = 'N';

	public NegativeTile() {
		
		setPrice(negTileCost);
		setSymbol(negSymbol);
	}
	/**
	 * Scores the word negatively
	 * @return
	 */
	public void negativeScore(Game game) {
		int minusScore = -15;
		//shouldnt effect player who placed,  lett should have special owner
		game.getCurrentPlayer().addScore(minusScore);
	}

	
	@Override
	public void enactTileAction(Game game, int row, int col) {
		//put owner check here
		negativeScore(game);
	}

}

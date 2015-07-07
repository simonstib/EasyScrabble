package edu.wayne.csc3200.hw4.core;



public class SkipTile extends Letter {

	private int skipTileCost = 12;
	private char skipSymbol = 'S';
	

	public SkipTile() {

		setPrice(skipTileCost);
		setSymbol(skipSymbol);
	}

	public void skipTurn(Game game){
		game.nextTurn();
	}
	
	

	@Override
	public void enactTileAction(Game game, int row, int col) {
		
		skipTurn(game);
	}

}

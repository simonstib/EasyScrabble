package edu.wayne.csc3200.hw4.core;

import java.util.Collections;

public class ReverseTile extends Letter {

	private int revTileCost = 20;
	private char revTileSymbol = 'R';

	public ReverseTile() {
		setPrice(revTileCost);
		setSymbol(revTileSymbol);
	}

	public void reverseTurn(){
		Collections.reverse(getGame().getTurnOrder());
	}
	

	@Override
	public void enactTileAction(Game game, int row, int col) {
		
		reverseTurn();
	}

}

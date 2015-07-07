package edu.wayne.csc3200.hw4.core;

public abstract class Letter {

	private char symbol;
	private int letterScore;
	private int price;
	private int ownerID;
	private Game game;

	public Letter() {
	}

	public Letter(char symbol, int points) {
		this.symbol = symbol;
		this.letterScore = points;
	}

	public int getLetterScore() {
		return letterScore;
	}

	public void setLetterScore(int letterScore) {
		this.letterScore = letterScore;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char c) {
		this.symbol = c;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public abstract void enactTileAction(Game game, int row, int col);

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}

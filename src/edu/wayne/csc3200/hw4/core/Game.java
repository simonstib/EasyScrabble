package edu.wayne.csc3200.hw4.core;

import java.io.IOException;
import java.util.ArrayList;

import edu.wayne.csc3200.hw4.gui.ScrabbleGui;

public class Game {

	private int playerCount = 0;
	private int turnNumber = 0;
	private boolean gameOn;
	private boolean turnOver;
	private boolean specialAct = false;

	private Player player;
	private ArrayList<Player> turnOrder;
	private Board gameBoard;
	private Dictionary dict;
	private Bag gameBag;


	ScrabbleGui gui;
	private Player currentPlayer = null;

	public Game() throws IOException {

		gameBag = new Bag();
		gameBag.populateBag();
		player = new Player();
		turnOrder = new ArrayList<Player>();
		gameBoard = new Board();
		gameBoard.initBoard();
		dict = new Dictionary();
	}

	/**
	 * Start the game by setting gameOn to true
	 */
	public void startGame() {
		setGui(new ScrabbleGui(this));
		gameOn = true;

	}

	/**
	 * Show the current player's special tile symbol
	 */
	public String displaySpecialTileSlot() {
		String s;
		char[] specialRackContents = new char[1];
		specialRackContents[0] = getCurrentPlayer().getSpecialTileSlot().get(0)
				.getSymbol();
		s = new String(specialRackContents);
		return s;
	}

	/**
	 * Test placement of special tile for board bounds
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean testSpecialPlacement(int row, int column) {

		if ((row < 0 || row > 14) && (column < 0 || column > 14))
			return true;
		else
			return false;
	}

	/**
	 * Place the current player's special tile onto the board
	 * 
	 * @param row
	 * @param column
	 */
	public void placeSpecialTile(int row, int column) {
		Letter blank = new CharLetter(' ', 0);
		getGameBoard().getSquare(row, column).setSpecialLetter(
				getCurrentPlayer().getSpecialTileSlot().get(0));
		getGameBoard().getSquare(row, column).setContainsSpecial(true);
		getCurrentPlayer().getSpecialTileSlot().clear();
		getCurrentPlayer().getSpecialTileSlot().add(blank);

	}

	/**
	 * add special tile to current players special tile slot, must have enough
	 * points
	 * 
	 * @param specialTile
	 *            option from dialog
	 * @return
	 */
	public boolean addSpecialTile(String specialTile) {

		Letter special = selectSpecialTile(specialTile);
		if (getCurrentPlayer().spendPoints(special.getPrice())) {
			getCurrentPlayer().getSpecialTileSlot().clear();
			special.setOwnerID(getCurrentPlayer().getID());
			getCurrentPlayer().getSpecialTileSlot().add(special);
			return true;
		} else
			return false;
	}

	/**
	 * checks the current players points against the cost of the special tile
	 * 
	 * @param specialTile
	 * @return
	 */
	public boolean checkPoints(String specialTile) {
		if (getCurrentPlayer().getPlayerScore() < selectSpecialTile(specialTile)
				.getPrice())
			return false;
		else
			return true;
	}

	/**
	 * Transform a string choice from gui into corresponding special tile object
	 * 
	 * @param specialTileSymbol
	 * @return
	 */
	private Letter selectSpecialTile(String specialTileSymbol) {
		Letter specialTile;
		switch (specialTileSymbol) {
		case "B":
			specialTile = new BoomTile();
			break;

		case "L":
			specialTile = new LoseScoreTile();
			break;

		case "N":
			specialTile = new NegativeTile();
			break;

		case "R":
			specialTile = new ReverseTile();
			break;
		case "S":
			specialTile = new SkipTile();
			break;

		default:
			specialTile = null;
			break;

		}
		return specialTile;
	}

	/**
	 * Return the player with the highest score.
	 * 
	 * @return Player
	 */
	public Player declareWinner() {
		int maxScore = 0;
		Player result = new Player();
		for (Player p : turnOrder) {
			int test = p.getPlayerScore();
			if (test > maxScore) {
				maxScore = test;
				result = p;
			}
		}
		return result;
	}

	/**
	 * End the game if Bag is empty, set gameOn to false
	 * 
	 * @return true if bag is empty
	 */
	public boolean gameEnded() {
		if (gameBag.isEmpty())
			gameOn = false;
		return gameOn;
	}

	/**
	 * add player to the player arrayList up to 4 players
	 * 
	 * @param p
	 * @throws IOException
	 */
	public void addPlayer(String name) {

		Player newPlayer = new Player(name);
		playerCount++;
		newPlayer.setID(playerCount);
		turnOrder.add(newPlayer);
	}

	/**
	 * Set the current player to the next player in the turn order array
	 */
	public void nextTurn() {
		if (turnNumber == turnOrder.size() - 1)
			turnNumber = 0;
		else
			turnNumber++;
		setCurrentPlayer(turnOrder.get(turnNumber));
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Bag getGameBag() {
		return gameBag;
	}

	public void setGameBag(Bag gameBag) {
		this.gameBag = gameBag;
	}

	public boolean isTurnOver() {
		return turnOver;
	}

	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void addPlayerCount() {
		this.playerCount++;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public boolean isGameOn() {
		return gameOn;
	}

	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}

	public Dictionary getGameDict() {
		return dict;
	}

	public void setGameDict(Dictionary dict) {
		this.dict = dict;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ScrabbleGui getGui() {
		return gui;
	}

	public void setGui(ScrabbleGui gui) {
		this.gui = gui;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}



	public ArrayList<Player> getTurnOrder() {
		return turnOrder;
	}

	public void setTurnOrder(ArrayList<Player> turnOrder) {
		this.turnOrder = turnOrder;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}



	public boolean isSpecialAct() {
		return specialAct;
	}

	public void setSpecialAct(boolean specialAct) {
		this.specialAct = specialAct;
	}

}

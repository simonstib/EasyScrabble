package edu.wayne.csc3200.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {

	private String name;
	private int score;
	private int ID;
	private int handSize;
	private int handMax = 7;
	private ArrayList<Letter> rack;
	private ArrayList<Letter> specialTileSlot;	
	
	public Player() {
	}

	public Player(String name) {
		Letter blank = new CharLetter(' ',0);
		this.name = name;
		score = 0;
		handSize = 0;
		rack = new ArrayList<Letter>();
		specialTileSlot = new ArrayList<Letter>();
		specialTileSlot.add(blank);
	}

	/**
	 * Fills player's rack with new letter tiles from the bag.
	 * 
	 * @param bag
	 */
	public void fillRack(Bag bag) {
		handSize = rack.size();
		while (handSize < handMax) {
			Letter test = bag.getTileFromBag();
			test.setOwnerID(getID());
			rack.add(test);
			handSize++;
			// remove new letter from bag
			bag.removeTileFromBag(test);
		}
	}

	/**
	 * Removes a Letter tile from the rack
	 * 
	 * @param Letter
	 */
	public void removeLetter(Letter Letter) {
		boolean isFound = false;
		int index = 0;

		if (!rack.isEmpty()) {
			Letter emptySpace = new CharLetter('!', 0);
			for (int i = 0; i < rack.size(); i++) {
				Letter test = rack.get(i);
				if (test.getSymbol() == Letter.getSymbol()) {
					isFound = true;
					index = i;
				}
			}
			// Only take out one instance of the found letter
			if (isFound == true) {
				rack.remove(index);
				rack.add(emptySpace);
				handSize = rack.size();
			}
		}

	}

	/**
	 * Checks for flag '!' in rack then adds a random letter tile in each flag's
	 * place.
	 */
	public void rerack(Bag bag) {
		for (int count = 0; count < getRack().size(); count++) {
			while (getRack().get(count).getSymbol() == '!') {
				getRack().remove(count);
				fillRack(bag);
			}
		}
	}

	/**
	 * removes the letter from the rack to be played on board
	 * 
	 * @param charLetter
	 *            - the char of the letter in the rack
	 */
	public void addToPlay(char charLetter) {
		// send the tile found to the board
		Letter tile = new CharLetter();
		tile.setSymbol(charLetter);

		removeLetter(tile);
	}

	/**
	 * Check rack for the Letter by it's char
	 * 
	 * @param charLetter
	 * @return true if found in rack
	 */
	public boolean checkRack(char charLetter) {
		boolean found = false;
		for (int i = 0; i < rack.size(); i++) {
			if (rack.get(i).getSymbol() == charLetter)
				found = true;
		}
		return found;
	}

	/**
	 * Get a Letter to place in a square
	 * 
	 * @param charLetter
	 * @return Letter object from rack
	 */
	public Letter toPlace(char charLetter) {
		Letter newLetter = new CharLetter();
		for (int i = 0; i <rack.size(); i++) {
			if (rack.get(i).getSymbol() == charLetter)
				newLetter = rack.get(i);
		}
		return newLetter;
	}

	/**
	 * Add more points to the score of the player.
	 * 
	 * @param newPoints
	 */
	public void addScore(int newPoints) {
		int x = getPlayerScore();
		x += newPoints;
		setPlayerScore(x);
	}
	/**
	 * checks player score then removes points of the tile
	 * @param tileCost
	 * @return 
	 */
	public boolean spendPoints(int tileCost){
		int x = getPlayerScore();
		if(x < tileCost)
			return false;
		else{
			x -= tileCost;
			setPlayerScore(x);
			return true;
		}
		
	}
	
	
	/**
	 * Calculates the points for playing a word
	 * @param testWord
	 * @return points awarded to player score
	 */
	public int calcScore(ArrayList<Square> testWord) {
		int letterScore = 0;
		int squareBonus = 1;

		int finalScore = 0;
		for (int i = 0; i < testWord.size(); i++) {
			
			letterScore += testWord.get(i).getContainedLetter()
					.getLetterScore()
					* testWord.get(i).getLetterBonus();
			
			squareBonus *= testWord.get(i).getSquareBonus();	
			
		}
		
		finalScore = (letterScore * squareBonus);
		return finalScore;
	}


	/**
	 * prints the elements of the player's rack
	 */
	public void printPlayerRack() {

		System.out.print(this.getName() +"'s Rack: ");
		Iterator<Letter> iterator = rack.iterator();
		while (iterator.hasNext())
			System.out.print(iterator.next().getSymbol() + ", ");
		System.out.println();
	}
	/**
	 * Display a string of the players rack
	 * @return string of rack
	 */
	public String displayPlayerRack(){
		char[] rackContents = new char[this.getRack().size()];
		for(int i =0;i<rack.size();i++)
			rackContents[i] = rack.get(i).getSymbol();
		String s = new String(rackContents);
		return s;
	}

	public ArrayList<Letter> getRack() {
		return rack;
	}

	public void setRack(ArrayList<Letter> letterRack) {
		this.rack = letterRack;
	}

	

	public void setPlayerScore(int s) {
		this.score = s;
	}

	public int getPlayerScore() {
		return score;
	}
	
	public String displayPlayerScore(){
		int i = this.getPlayerScore();
		String s = Integer.toString(i);
		return s;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Letter> getSpecialTileSlot() {
		return specialTileSlot;
	}

	public void setSpecialTileSlot(ArrayList<Letter> specialTileSlot) {
		this.specialTileSlot = specialTileSlot;
	}

	
}

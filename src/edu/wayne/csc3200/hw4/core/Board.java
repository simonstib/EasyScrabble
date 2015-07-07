package edu.wayne.csc3200.hw4.core;

import java.util.ArrayList;

public class Board {

	private Square[][] squaresBoard;

	private ArrayList<Square> wordFormed;
	public static final int BOARDSIZE = 15;
	private int[] doubleWord = { 1, 1, 2, 2, 3, 3, 4, 4, 13, 1, 12, 2, 11, 3,
			10, 4, 4, 10, 3, 11, 2, 12, 1, 13, 10, 10, 11, 11, 12, 12, 13, 13,
			14, 14 };
	private int[] tripleWord = { 0,0, 0,7, 0,14, 7,0, 14,7, 14,0, 14,7,
			14,14, 7,14 };
	private int[] doubleLetter = { 0, 3, 0, 11, 2, 6, 2, 8, 3, 0, 3, 7, 3, 14,
			6, 2, 6, 6, 6, 8, 6, 12, 7, 3, 7, 11, 8, 2, 8, 6, 8, 8, 8, 12, 11,
			0, 11, 7, 11, 14, 12, 6, 12, 8, 14, 3, 14, 11 };
	private int[] tripleLetter = { 1, 5, 1, 9, 5, 1, 5, 5, 5, 9, 5, 13, 9, 1,
			9, 5, 9, 9, 9, 13, 13, 5, 13, 9 };
	private String testWord;

	public Board() {}

	/**
	 * Get Square object at (i,j) coordinates from board
	 * 
	 * @param row
	 *            Row number
	 * @param column
	 *            Column number
	 * @return square at coordinates
	 */
	public Square getSquare(int row, int column) {

		return squaresBoard[row][column];
	}

	/**
	 * Get the char of a tile contained in the square
	 * 
	 * @param row
	 *            row location of board
	 * @param j
	 *            column location of board
	 * @return the Letter char contained in the square
	 */
	public char getSquareSymbol(int row, int column) {
		return squaresBoard[row][column].getContainedchar();
	
	}

	/**
	 * initializes a new board object with boardSize rows and columns of Squares
	 */
	public void initBoard() {

		squaresBoard = new Square[BOARDSIZE][BOARDSIZE];
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				squaresBoard[i][j] = new RegSquare();
			}
		}
		for (int i = 0; i < doubleWord.length; i += 2) {
			squaresBoard[doubleWord[i]][doubleWord[i + 1]] = new DoubleWordSquare();
		}
		
		for (int i = 0; i < doubleLetter.length; i += 2) {
			squaresBoard[doubleLetter[i]][doubleLetter[i + 1]] = new DoubleLetterSquare();
		}
		
		for (int i = 0; i < tripleWord.length; i += 2) {
			squaresBoard[tripleWord[i]][tripleWord[i + 1]] = new TripleWordSquare();
		}
		
		for (int i = 0; i < tripleLetter.length; i += 2) {
			squaresBoard[tripleLetter[i]][tripleLetter[i + 1]] = new TripleLetterSquare();
		}
	}


	/**
	 * returns true if board location is occupied by a player
	 * 
	 * @param row
	 *            row coordinate
	 * @param column
	 *            column coordinate
	 * @return true if occupied
	 */
	public boolean isOccupied(int row, int column) {
		// works for non id returns
		return getSquare(row, column).isPlayerHasPlaced();
	}

	/**
	 * Get the board
	 * 
	 * @return board 2-d array of Squares
	 */
	public Square[][] getBoard() {
		return squaresBoard;
	}
	
	/**
	 * Take a string and place the corresponding tiles onto the board from the
	 * rack then check it against the dictionary
	 * 
	 * @param word
	 * @param startRow
	 * @param startCol
	 * @param direction
	 *            - 0 goes right, 1 goes down
	 * @param player
	 * @param dict
	 */
	public void placeOnBoard(String word, int startRow, int startCol,
			int direction, Player player, Dictionary dict,Game game) {
		
		wordFormed = new ArrayList<Square>();
		word.trim();

		for (int i = 0; i < word.length(); i++) {
			if (!(player.checkRack(word.charAt(i)))) {
				System.out.println("Letter " + word.charAt(i)
						+ " not found in rack.");
				return;
			}
		}

		// check if coordinates are in bounds
		if ((startRow < 0 || startRow > 14) && (startCol < 0 || startCol > 14)
				&& (direction < 0 || direction > 1))
			return;

		// check if board is occupied then place the letters
		
		wordPlace(word, startRow, startCol, direction, player,game);
		
		

		if (validate(testWord, dict)) {
			player.addScore(player.calcScore(wordFormed));
			for (int i = 0; i < word.length(); i++) {
				player.addToPlay(word.charAt(i));
			}
		}
		
	}

	/**
	 * Places a word on the board and sets the complete word to a String
	 * 
	 * @param newWord
	 * @param row
	 * @param col
	 * @param direction
	 *            - 0 =right, 1 = down
	 * @param player
	 *            - the player to place tiles
	 */
	private void wordPlace(String newWord, int row, int col, int direction,
			Player player,Game game) {
		int end = 0;
		int i = 0;

		if (direction == 0) {
			// go right, col ++

			while (end < newWord.length()) {
				
				if ((isOccupied(row, col + i))) {
					
					wordFormed.add(getSquare(row, col + i));
					place(player.toPlace(newWord.charAt(end)), row, col + i + 1);
					Square square = getSquare(row, col + i+1);
					wordFormed.add(getSquare(row, col + i + 1));
					if (square.isContainsSpecial()
							&& square.containsLetter()
							&& (square.getSpecialLetter().getOwnerID() != game
									.getCurrentPlayer().getID())) {
						square.setContainsSpecial(false);
						game.setSpecialAct(true);

					}
					i++;

				} else {
					wordFormed.add(getSquare(row, col + i));
					place(player.toPlace(newWord.charAt(end)), row, col + i);
					Square square = getSquare(row, col + i);
					if (square.isContainsSpecial()
							&& square.containsLetter()
							&& (square.getSpecialLetter().getOwnerID() != game
									.getCurrentPlayer().getID())) {
						square.setContainsSpecial(false);
						game.setSpecialAct(true);

					}
					i++;
				}
				end++;
			}
		} else if (direction == 1) {
			// go down, row++
			while (end < newWord.length()) {

				if ((isOccupied(row + i, col))) {
					wordFormed.add(getSquare(row + i, col));
					
					place(player.toPlace(newWord.charAt(end)), row + i + 1, col);
					wordFormed.add(getSquare(row + i + 1, col));
					Square square = getSquare(row+ i+1, col );
					if (square.isContainsSpecial()
							&& square.containsLetter()
							&& (square.getSpecialLetter().getOwnerID() != game
									.getCurrentPlayer().getID())) {
						square.setContainsSpecial(false);
						game.setSpecialAct(true);

					}
					i++;

				} else {
					wordFormed.add(getSquare(row + i, col));
					
					
						place(player.toPlace(newWord.charAt(end)), row + i, col);
					Square square = getSquare(row+ i, col );
					if (square.isContainsSpecial()
							&& square.containsLetter()
							&& (square.getSpecialLetter().getOwnerID() != game
									.getCurrentPlayer().getID())) {
						square.setContainsSpecial(false);
						game.setSpecialAct(true);

					}
					i++;
				}
				end++;
			}
		}
		if(game.isSpecialAct())
			getSquare(row,col).getSpecialLetter().enactTileAction(game, row, col);
		testWord = listToString(wordFormed);
	}

	
	/**
	 * Converts an ArrayList<Square> to a string
	 * 
	 * @param list
	 * @return String of the contained char
	 */
	public String listToString(ArrayList<Square> list) {
		char[] tempAry = new char[list.size()];

		for (int i = 0; i < list.size(); i++) {
			tempAry[i] = list.get(i).getContainedchar();
		}
		String a = new String(tempAry);
		return a;
	}

	/**
	 * Display the symbol of the letter in the square
	 * 
	 * @param list
	 */
	public void displayList(ArrayList<Square> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getContainedchar() + ",");
		}
	}

	/**
	 * Look up String against the Dictionary
	 * 
	 * @param s
	 * @param dict
	 * @return true if word is in Dictionary
	 */
	public boolean validate(String s, Dictionary dict) {
		return dict.lookUpWord(s);
	}

	/**
	 * Places a Letter tile onto the board.
	 * 
	 * @param tile
	 *            - Letter tile to be placed
	 * @param row
	 * @param col
	 */
	public void place(Letter tile, int row, int col) {
		Square s = getSquare(row, col);
		s.setContainedLetter(tile);
		squaresBoard[row][col] = s;
		getSquare(row, col).setPlayerHasPlaced(true);
	}
	



	public ArrayList<Square> getWordFormed() {
		return wordFormed;
	}

	public void setWordFormed(ArrayList<Square> wordFormed) {
		this.wordFormed = wordFormed;
	}

	public String getTestWord() {
		return testWord;
	}

	public void setTestWord(String testWord) {
		this.testWord = testWord;
	}

	public int getBoardSize() {
		return BOARDSIZE;
	}

	

}

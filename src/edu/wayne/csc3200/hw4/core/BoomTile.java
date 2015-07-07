package edu.wayne.csc3200.hw4.core;

public class BoomTile extends Letter {

	private int boomTilePrice = 50;
	private char boomSymbol = 'B';
	
	public BoomTile() {
		
		setPrice(boomTilePrice);
		setSymbol(boomSymbol);
	}
	
	public void boom(Game game,int row , int col){
		int newRow = row-2;
		int newCol = col-2;
		Letter blank = new CharLetter(' ',0);
		if((row> 2 && col >2) || (row <12 && col<12) ){
			for(int i =newRow; i< row+2;i++ ){
				for(int j = newCol; j<col+3; j++){
					game.getGameBoard().getSquare(i, j).setContainedLetter(blank);
					game.getGameBoard().getSquare(i, j).setContainsLetter(false);
				}
			}
		}
			
		else{
			for(int i =newRow; i< row+2;i++ ){
				for(int j = newCol; j<col+2; j++){
					game.getGameBoard().getSquare(i, j).setContainedLetter(blank);
					game.getGameBoard().getSquare(i, j).setContainsLetter(false);
				}
			}
		}
			
	}
	
	@Override
	public void enactTileAction(Game game, int row, int col) {
		
		boom(game,row,col);
	}

	
}

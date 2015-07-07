package edu.wayne.csc3200.hw4.core;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Bag {

	private int bagSize = 0;	
	private ArrayList<Letter> letterSet;	

	
	public Bag() {	
			letterSet = new ArrayList<Letter>();			
	}	
	
	/**
	 * generate a random integer bounded by "max"
	 * @param max top bound of range
	 * @return random int
	 */
	public int getRand(int max) {
		Random rand = new Random();
		return rand.nextInt(max);
	}
	/**
	 * check if bag is empty
	 * @return true if bag if empty
	 */
	public boolean isEmpty() {
		if (!(letterSet.isEmpty())) {
			
			return false;
		} else {
			
			return true;
		}
	}

	/**
	 * add 100 randomly generated tiles to the bag
	 */
	
	public void populateBag() {

		for (int aCount = 0; aCount < 9; aCount++) {
			Letter a = new CharLetter();
			a.setSymbol('a');
			a.setLetterScore(1);
			Letter c = new CharLetter();
			c.setSymbol('i');
			c.setLetterScore(1);
			letterSet.add(a);
			letterSet.add(c);
		}
		for (int bCount = 0; bCount < 12; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('e');
			b.setLetterScore(1);
			letterSet.add(b);
		}
		for (int bCount = 0; bCount < 8; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('o');
			b.setLetterScore(1);
			letterSet.add(b);
		}
		for (int bCount = 0; bCount < 6; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('n');
			b.setLetterScore(1);
			Letter a = new CharLetter();
			a.setSymbol('r');
			a.setLetterScore(1);
			Letter c = new CharLetter();
			c.setSymbol('t');
			c.setLetterScore(1);
			letterSet.add(c);
			letterSet.add(b);
			letterSet.add(a);
		}
		for (int bCount = 0; bCount < 4; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('l');
			b.setLetterScore(1);
			Letter a = new CharLetter();
			a.setSymbol('s');
			a.setLetterScore(1);
			Letter c = new CharLetter();
			c.setSymbol('u');
			c.setLetterScore(1);
			Letter d = new CharLetter();
			d.setSymbol('d');
			d.setLetterScore(2);
			letterSet.add(d);
			letterSet.add(c);
			letterSet.add(b);
			letterSet.add(a);
		}
		for (int bCount = 0; bCount < 3; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('g');
			b.setLetterScore(2);

			letterSet.add(b);
		}
		for (int bCount = 0; bCount < 2; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('b');
			b.setLetterScore(3);
			Letter a = new CharLetter();
			a.setSymbol('c');
			a.setLetterScore(3);
			Letter c = new CharLetter();
			c.setSymbol('m');
			c.setLetterScore(3);
			Letter d = new CharLetter();
			d.setSymbol('p');
			d.setLetterScore(3);
			Letter e = new CharLetter();
			e.setSymbol('f');
			e.setLetterScore(4);
			Letter f = new CharLetter();
			f.setSymbol('h');
			f.setLetterScore(4);
			Letter g = new CharLetter();
			g.setSymbol('v');
			g.setLetterScore(4);
			Letter h = new CharLetter();
			h.setSymbol('w');
			h.setLetterScore(4);
			Letter i = new CharLetter();
			i.setSymbol('y');
			i.setLetterScore(4);
			letterSet.add(a);
			letterSet.add(b);
			letterSet.add(c);
			letterSet.add(d);
			letterSet.add(e);
			letterSet.add(f);
			letterSet.add(g);
			letterSet.add(h);
			letterSet.add(i);
		}
		for (int bCount = 0; bCount < 1; bCount++) {
			Letter b = new CharLetter();
			b.setSymbol('k');
			b.setLetterScore(5);
			Letter a = new CharLetter();
			a.setSymbol('j');
			a.setLetterScore(8);
			Letter c = new CharLetter();
			c.setSymbol('x');
			c.setLetterScore(8);
			Letter d = new CharLetter();
			d.setSymbol('q');
			d.setLetterScore(10);
			Letter e = new CharLetter();
			e.setSymbol('z');
			e.setLetterScore(10);
			letterSet.add(a);
			letterSet.add(b);
			letterSet.add(c);
			letterSet.add(d);
			letterSet.add(e);
		}
		bagSize = letterSet.size();
	}
	/**
	 * get a letter object from the bag
	 * @return letter object with char and score
	 */	
	public Letter getTileFromBag() {
		int i = letterSet.size();
		return letterSet.get(getRand(i));
	}
	/**
	 * remove a letter from the bag
	 * @param normalLetter letter to remove
	 */
	public void removeTileFromBag(Letter normalLetter) {
		for (int i = 0; i < letterSet.size(); i++) {

			if (letterSet.get(i).equals(normalLetter)) {
				letterSet.remove(normalLetter);
				bagSize = letterSet.size();
			}
		}
	}

	/**
	 * display contents of the bag
	 * testing purposes
	 */
	public void printBag() {
		System.out.print("bag: ");
		Iterator<Letter> iterator = this.getLetterSet().iterator();
		while (iterator.hasNext())
			System.out.print(iterator.next().getSymbol() + ", ");
		System.out.println(".");
	}

	public int getBagSize() {
		return bagSize;
	}

	public void setBagSize(int bagSize) {
		this.bagSize = bagSize;
	}

	public ArrayList<Letter> getLetterSet() {
		return letterSet;
	}

	public void setLetterSet(ArrayList<Letter> letterSet) {
		this.letterSet = letterSet;
	}
}

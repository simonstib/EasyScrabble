package edu.wayne.csc3200.hw4.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Dictionary  {
	private Set<String> words = new HashSet<String>();
	private BufferedReader reader;
	

	
	public Dictionary() throws IOException{
	
		reader = new BufferedReader(new FileReader("C:/Users/Tibby/csc3200/hw4/assets/words.txt"));
		String next;
		while((next = reader.readLine()) != null){
			words.add(next);
		}
	}
	/**
	 * Checks the String against the Dictionary
	 * @param inWord
	 * @return true if foundS
	 */
	public boolean lookUpWord(String inWord){
		
		return words.contains(inWord);
	}

	public Set<String> getWords() {
		return words;
	}

	public void setWords(Set<String> words) {
		this.words = words;
	}
}

package edu.wayne.csc3200.hw4.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {

		Board b = new Board();
		b.initBoard();

		Letter l1 = new CharLetter('c', 1);

		b.place(l1, 7, 7);

		// test placing letters on the board
		assertEquals('c', b.getSquareSymbol(7, 7));
	}

}

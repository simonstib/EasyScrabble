package edu.wayne.csc3200.hw4.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {

		Game g = new Game();
		Dictionary dict = g.getGameDict();
		Board b = g.getGameBoard();
		Bag bag = g.getGameBag();
		ArrayList<Square> test = new ArrayList<Square>();

		
		g.addPlayer("bob");
		g.addPlayer("Jan");

		
		assertFalse(bag.isEmpty());
		assertFalse(b.isOccupied(7, 7));

		Letter l1 = new CharLetter('c', 1);
		Letter l2 = new CharLetter('a', 2);
		Letter l3 = new CharLetter('t', 3);
		Square s1 = new RegSquare(l1);
		Square s2 = new RegSquare(l2);
		Square s3 = new RegSquare(l3);
		g.getTurnOrder().get(0).getRack().add(l1);
		g.getTurnOrder().get(0).getRack().add(l2);
		g.getTurnOrder().get(0).getRack().add(l3);

		Letter l4 = new CharLetter('s', 1);

		g.getTurnOrder().get(1).getRack().add(l1);
		g.getTurnOrder().get(1).getRack().add(l2);
		g.getTurnOrder().get(1).getRack().add(l3);

		test.add(s1);
		test.add(s2);
		test.add(s3);

		// test dictionary against "bunt"
		String a = "bunt";
		assertTrue(b.validate(a, dict));
		
		// test that player has no score before placement and scoring
		assertEquals(0, g.getTurnOrder().get(0).getPlayerScore());
		b.placeOnBoard("cat", 9, 7, 0, g.getTurnOrder().get(0), dict,g);

		// test dictionary and placement of "cat" to board
		assertEquals(18, g.getTurnOrder().get(0).getPlayerScore());
		assertEquals("cat", b.getTestWord());
		
		// test that letters are removed by placement
		assertFalse(g.getTurnOrder().get(0).checkRack('c'));

		// test tiles on board help make new words
		b.place(l4, 10, 9);
		assertEquals(0, g.getTurnOrder().get(1).getPlayerScore());
		b.placeOnBoard("cat", 10, 7, 0, g.getTurnOrder().get(1), dict,g);
		assertEquals(14, g.getTurnOrder().get(1).getPlayerScore());
	}

}

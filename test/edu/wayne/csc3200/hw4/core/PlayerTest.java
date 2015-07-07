package edu.wayne.csc3200.hw4.core;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Player p1 = new Player("bob");
		Player p2 = new Player("Autumn");

		Bag bag = new Bag();
		bag.populateBag();
		p1.fillRack(bag);
		p1.addScore(5);

		Letter l1 = new CharLetter('c', 1);
		Letter l2 = new CharLetter('a', 2);

		p2.getRack().add(l1);
		p2.getRack().add(l2);

		// testing rack size and score manipulation
		assertEquals(7, p1.getRack().size());
		assertEquals(5, p1.getPlayerScore());

		// testing contents of rack
		assertTrue(p2.checkRack('c'));
		assertTrue(p2.checkRack('a'));

		// testing if returned letter has correct char
		assertEquals('a', p2.toPlace('a').getSymbol());

		// testing is letter tile is removed from rack
		p2.addToPlay('a');
		assertFalse(p2.checkRack('a'));

		// test that size hasnt changed and flag '!' is set
		assertEquals(2, p2.getRack().size());
		assertEquals('!', p2.getRack().get(1).getSymbol());

	}

}

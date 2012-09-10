package hqpc.game.bullsandcows.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuessResultTest {

	@Test
	public void testHashCode() {
		GuessResult firstGuessResult = new GuessResult(3, 1);
		GuessResult secondGuessResult = new GuessResult(3, 1);
		
		assertEquals(firstGuessResult.hashCode(), secondGuessResult.hashCode());
	}

	@Test
	public void testEqualsObject() {
		GuessResult firstGuessResult = new GuessResult(3, 1);
		GuessResult secondGuessResult = new GuessResult(3, 1);
		GuessResult thirdGuessResult = new GuessResult(3, 0);
		
		assertFalse(firstGuessResult.equals(null));
		
		assertTrue(firstGuessResult.equals(secondGuessResult));
		assertTrue(secondGuessResult.equals(firstGuessResult));
		assertFalse(firstGuessResult.equals(thirdGuessResult));
		assertFalse(thirdGuessResult.equals(firstGuessResult));
	}

	@Test
	public void testGeters() {
		GuessResult guessResult = new GuessResult(3, 1);
		
		assertEquals(3, guessResult.getBulls());
		assertEquals(1, guessResult.getCows());
	}
}

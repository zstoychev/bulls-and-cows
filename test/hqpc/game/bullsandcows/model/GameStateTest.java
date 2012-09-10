package hqpc.game.bullsandcows.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameStateTest {
	@Test
	public void testGetSecretNumber() {
		BullsAndCowsNumber expected = new BullsAndCowsNumber(1234);
		GameState gameState = new GameState(expected);
		BullsAndCowsNumber actual = gameState.getSecretNumber();
		assertEquals(expected, actual);
	}

	@Test
	public void testIncrementNumberOfAttempts() {
		GameState gameState = new GameState(new BullsAndCowsNumber());
		gameState.incrementNumberOfAttempts();
		int expected = 1;
		int actual = gameState.getNumberOfAttempts();
		assertEquals(expected, actual);
	}

	@Test
	public void testRevealRandomDigit() {
		GameState gameState = new GameState(new BullsAndCowsNumber());
		gameState.revealRandomDigit();
		gameState.revealRandomDigit();
		
		int expectedNumberOfRevealedDigits = 2;
		int actualNumberOfRevealedDigits = gameState.getNumberOfRevealedDigits();
		assertEquals(expectedNumberOfRevealedDigits, actualNumberOfRevealedDigits);
		
		int expectedTrueValues = 2;
		int actualTrueValues = 0;
		for (boolean isDigitRevealed : gameState.getRevealedDigits()) {
			if (isDigitRevealed) {
				actualTrueValues++;
			}
		}
		assertEquals(expectedTrueValues, actualTrueValues);
	}
}

package hqpc.game.bullsandcows.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BullsAndCowsNumberTest {
	@Test
	public void testGetDigitsCornerCase() {
		BullsAndCowsNumber minNumber = new BullsAndCowsNumber(0);
		
		List<Integer> expectedMinNumberDigits = new ArrayList<Integer>();
		for (int i = 0; i < BullsAndCowsNumber.NUMBER_OF_DIGITS; i++) {
			expectedMinNumberDigits.add(0);
		}
		List<Integer> actualMinNumberDigits = minNumber.getDigits();
		assertEquals(expectedMinNumberDigits, actualMinNumberDigits);
	}
	
	@Test
	public void testGetDigits() {
		BullsAndCowsNumber number = new BullsAndCowsNumber(9484);
		
		int[] expectedDigits = {9, 4, 8, 4};
		List<Integer> actualDigits = number.getDigits();
		
		assertEquals(expectedDigits.length, actualDigits.size());
		for (int i = 0; i < expectedDigits.length; i++) {
			assertEquals(expectedDigits[i], (int) actualDigits.get(i));
		}
	}

	@Test
	public void testTestAgainstNumberDifferentDigits() {
		BullsAndCowsNumber number = new BullsAndCowsNumber(1234);
		BullsAndCowsNumber otherNumber = new BullsAndCowsNumber(9243);

		GuessResult expectedResult = new GuessResult(1, 2);
		GuessResult actualResult = number.testAgainstNumber(otherNumber);
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testTestAgainstNumberRepeatedDigits() {
		BullsAndCowsNumber number = new BullsAndCowsNumber(7725);
		BullsAndCowsNumber otherNumber = new BullsAndCowsNumber(2375);

		GuessResult expectedResult = new GuessResult(1, 2);
		GuessResult actualResult = number.testAgainstNumber(otherNumber);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testTestAgainstNumberOverlappingDigits() {
		BullsAndCowsNumber number = new BullsAndCowsNumber(2424);
		BullsAndCowsNumber otherNumber = new BullsAndCowsNumber(4144);

		GuessResult expectedResult = new GuessResult(1, 1);
		GuessResult actualResult = number.testAgainstNumber(otherNumber);
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testEquals() {
		BullsAndCowsNumber firstNumber = new BullsAndCowsNumber(342);
		BullsAndCowsNumber secondNumber = new BullsAndCowsNumber(342);
		BullsAndCowsNumber thirdNumber = new BullsAndCowsNumber(3420);
		
		assertFalse(firstNumber.equals(null));

		assertTrue(firstNumber.equals(secondNumber));
		assertTrue(secondNumber.equals(firstNumber));
		assertFalse(firstNumber.equals(thirdNumber));
		assertFalse(thirdNumber.equals(firstNumber));
	}
	
	@Test
	public void testHashCode() {
		BullsAndCowsNumber firstNumber = new BullsAndCowsNumber(342);
		BullsAndCowsNumber secondNumber = new BullsAndCowsNumber(342);
		
		assertEquals(firstNumber.hashCode(), secondNumber.hashCode());
	}
}

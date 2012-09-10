package utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NumberUtilsTest {
	@Test
	public void testNumberToListOfDigitsNoMinLimit() {
		List<Integer> expected = Arrays.asList(new Integer[] {
			9, 3, 0, 0, 3, 3, 1
		});
		List<Integer> actual = NumberUtils.numberToListOfDigits(9300331, 0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberToListOfDigitsGreaterThanMinLimit() {
		List<Integer> expected = Arrays.asList(new Integer[] {
				9, 3, 0, 0, 3, 3, 1
		});
		List<Integer> actual = NumberUtils.numberToListOfDigits(9300331, 6);
			
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberToListOfDigitsLowerThanMinLimit() {
		List<Integer> expected = Arrays.asList(new Integer[] {
				0, 0, 0, 0, 3, 3, 1
		});
		List<Integer> actual = NumberUtils.numberToListOfDigits(331, 7);
			
		assertEquals(expected, actual);
	}
}

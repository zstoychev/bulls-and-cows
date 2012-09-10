package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberUtils {
	public static List<Integer> numberToListOfDigits(int number, int minimumNumberOfDigits) {
		List<Integer> digits = new ArrayList<Integer>();
		
		digits.add(number % 10);
		for (int i = number / 10; i > 0; i /= 10) {
			digits.add(i % 10);
		}
		
		for (int i = digits.size(); i < minimumNumberOfDigits; i++) {
			digits.add(0);
		}
		
		Collections.reverse(digits);
		
		return digits;
	}
}

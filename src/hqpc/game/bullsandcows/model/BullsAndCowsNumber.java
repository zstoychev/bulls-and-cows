package hqpc.game.bullsandcows.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import utils.NumberUtils;

public final class BullsAndCowsNumber {
	private final List<Integer> digits;

	public static final int NUMBER_OF_DIGITS = 4;

	public BullsAndCowsNumber() {
		this(new Random().nextInt(
			(int) Math.pow(10, NUMBER_OF_DIGITS)));
	}

	public BullsAndCowsNumber(int number) {
		if (number < 0) {
			throw new IllegalArgumentException();
		}

		this.digits = NumberUtils.numberToListOfDigits(number, NUMBER_OF_DIGITS);
		
		if (this.digits.size() != NUMBER_OF_DIGITS) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int hashCode() {
		return digits.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BullsAndCowsNumber other = (BullsAndCowsNumber) obj;

		return  digits.equals(other.digits);
	}

	public List<Integer> getDigits() {
		return Collections.unmodifiableList(digits);
	}

	public GuessResult testAgainstNumber(BullsAndCowsNumber number) {
		List<Integer> digits = getDigits();
		List<Integer> otherNumberDigits = number.getDigits();
		boolean[] usedDigitsInThisNumber = new boolean[4];
		boolean[] usedDigitsInOtherNumber = new boolean[4];

		int bulls = 0;

		for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
			if (otherNumberDigits.get(i).equals(digits.get(i))) {
				bulls++;

				usedDigitsInThisNumber[i] = true;
				usedDigitsInOtherNumber[i] = true;
			}
		}

		int cows = 0;

		for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
			if (!usedDigitsInOtherNumber[i]) {
				for (int j = 0; j < 4; j++) {
					if (otherNumberDigits.get(i).equals(digits.get(j)) && !usedDigitsInThisNumber[j]) {
						cows++;

						usedDigitsInOtherNumber[i] = true;
						usedDigitsInThisNumber[j] = true;

						break;
					}
				}
			}
		}

		return new GuessResult(bulls, cows);
	}
}

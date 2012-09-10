package hqpc.game.bullsandcows.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameState {
	private BullsAndCowsNumber secretNumber;
	private int numberOfAttempts;
	private int numberOfRevealedDigits;
	private List<Boolean> revealedDigits;

	public GameState(BullsAndCowsNumber secretNumber) {
		if (secretNumber == null) {
			throw new NullPointerException();
		}

		this.secretNumber = secretNumber;
		this.numberOfAttempts = 0;
		this.numberOfRevealedDigits = 0;
		this.revealedDigits = new ArrayList<Boolean>();
		for (int i = 0; i < BullsAndCowsNumber.NUMBER_OF_DIGITS; i++) {
			this.revealedDigits.add(false);
		}
	}

	public BullsAndCowsNumber getSecretNumber() {
		return secretNumber;
	}

	public void incrementNumberOfAttempts() {
		numberOfAttempts++;
	}

	public void revealRandomDigit() {
		if (numberOfRevealedDigits == BullsAndCowsNumber.NUMBER_OF_DIGITS) {
			throw new IllegalStateException();
		}

		int numberOfDigitToBeRevealed = 1 + new Random().nextInt(
			BullsAndCowsNumber.NUMBER_OF_DIGITS - numberOfRevealedDigits);

		int position = 0;
		int numberOfPassedUnrevealedDigits = 0;

		for (; position < BullsAndCowsNumber.NUMBER_OF_DIGITS; position++) {
			if (!revealedDigits.get(position)) {
				numberOfPassedUnrevealedDigits++;
			}

			if (numberOfPassedUnrevealedDigits == numberOfDigitToBeRevealed) {
				break;
			}
		}

		numberOfRevealedDigits++;
		revealedDigits.set(position, true);
	}
	
	public List<Boolean> getRevealedDigits() {
		return Collections.unmodifiableList(revealedDigits);
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public int getNumberOfRevealedDigits() {
		return numberOfRevealedDigits;
	}
}

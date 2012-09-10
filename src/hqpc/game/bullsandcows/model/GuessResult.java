package hqpc.game.bullsandcows.model;

public final class GuessResult {
	private final int bulls;
	private final int cows;

	public GuessResult(int bulls, int cows) {
		if (bulls < 0 || cows < 0 || bulls + cows > BullsAndCowsNumber.NUMBER_OF_DIGITS) {
			throw new IllegalArgumentException();
		}

		this.bulls = bulls;
		this.cows = cows;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bulls;
		result = prime * result + cows;
		return result;
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
		GuessResult other = (GuessResult) obj;
		
		return bulls == other.bulls && cows == other.cows;
	}

	public int getBulls() {
		return bulls;
	}

	public int getCows() {
		return cows;
	}	
}

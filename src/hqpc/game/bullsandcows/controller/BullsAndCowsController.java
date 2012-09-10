package hqpc.game.bullsandcows.controller;

import hqpc.game.bullsandcows.model.BullsAndCowsNumber;
import hqpc.game.bullsandcows.model.GameState;
import hqpc.game.bullsandcows.model.GuessResult;
import hqpc.game.bullsandcows.ui.BullsAndCowsUI;
import hqpc.game.scoreboard.Scoreboard;

public class BullsAndCowsController {
	private BullsAndCowsUI ui;
	private Scoreboard scoreboard;
	private GameState currentGameState;

	public static final int SCOREBOARD_SIZE = 5;

	public BullsAndCowsController(BullsAndCowsUI ui) {
		this(ui, new Scoreboard(SCOREBOARD_SIZE), null);
	}
	
	public BullsAndCowsController(BullsAndCowsUI ui, Scoreboard startingScoreboard, GameState startingGameState) {
		if (ui == null || startingScoreboard == null) {
			throw new NullPointerException();
		}

		this.ui = ui;
		this.scoreboard = startingScoreboard;
		this.currentGameState = startingGameState;
	}

	private class CommandProcessor implements BullsAndCowsCommandProcessor {
		@Override
		public void processCommandTop() {
			ui.showTopScores(scoreboard);
		}

		@Override
		public void processCommandHelp() {
			if (currentGameState.getNumberOfRevealedDigits() != BullsAndCowsNumber.NUMBER_OF_DIGITS) {
				currentGameState.revealRandomDigit();
				ui.showHelp(currentGameState.getSecretNumber().getDigits(),
					currentGameState.getRevealedDigits());
			} else {
				ui.showNoMoreHelpIndication();
			}
		}

		@Override
		public void processCommandRestart() {
			currentGameState = new GameState(new BullsAndCowsNumber());
			ui.restart();
		}

		@Override
		public void processCommandExit() {
			ui.exit();
		}

		@Override
		public void processCommandGuess(String number) {
			int testValue = Integer.parseInt(number);

			GuessResult guessResult = currentGameState.getSecretNumber().testAgainstNumber(
					new BullsAndCowsNumber(testValue));
			currentGameState.incrementNumberOfAttempts();

			int bulls = guessResult.getBulls();

			if (bulls < BullsAndCowsNumber.NUMBER_OF_DIGITS) {
				ui.showWrongGuessIndication(guessResult);
			} else {
				ui.showCorrectGuessIndication(currentGameState.getNumberOfAttempts(),
					currentGameState.getNumberOfRevealedDigits());

				if (currentGameState.getNumberOfRevealedDigits() == 0) {
					if (scoreboard.isTopScore(currentGameState.getNumberOfAttempts())) {
						
						String name = ui.requestNameForScoreboard();
						scoreboard.addScore(name, currentGameState.getNumberOfAttempts());
					} else {
						ui.showNotAddedToScoreboardIndication();
					}
				}
				
				ui.showTopScores(scoreboard);
				ui.restart();
			}
		}
	}

	public void start() {
		if (this.currentGameState == null) {
			this.currentGameState = new GameState(new BullsAndCowsNumber());
		}

		ui.start(new CommandProcessor());
	}
}

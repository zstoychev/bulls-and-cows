package hqpc.game.bullsandcows.ui;

import java.util.List;

import hqpc.game.bullsandcows.controller.BullsAndCowsCommandProcessor;
import hqpc.game.bullsandcows.model.GuessResult;
import hqpc.game.scoreboard.Scoreboard;

public interface BullsAndCowsUI {
	public void start(BullsAndCowsCommandProcessor commandProcessor);
	public void showTopScores(Scoreboard scoreboard);
	public void showCorrectGuessIndication(int numberOfAttempts, int numberOfRevealedDigits);
	public void showWrongGuessIndication(GuessResult guessResult);
	public String requestNameForScoreboard();
	public void showNotAddedToScoreboardIndication();
	public void showHelp(List<Integer> digits, List<Boolean> revealedDigits);
	public void showNoMoreHelpIndication();
	public void restart();
	public void exit();
}

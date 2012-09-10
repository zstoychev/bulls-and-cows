package hqpc.game.bullsandcows.ui;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;
import hqpc.game.bullsandcows.controller.BullsAndCowsCommandProcessor;
import hqpc.game.bullsandcows.model.BullsAndCowsNumber;
import hqpc.game.bullsandcows.model.GuessResult;
import hqpc.game.scoreboard.Scoreboard;
import hqpc.game.scoreboard.ScoreboardEntry;

public class ConsoleUI implements BullsAndCowsUI {
	private PrintStream output;
	private Scanner input;
	private boolean exitMainLoop;
	private boolean restartGame;
	private BullsAndCowsCommandProcessor commandProcessor;
	
	public ConsoleUI(PrintStream output, Reader input) {
		if (output == null || input == null) {
			throw new NullPointerException();
		}

		this.output = output;
		this.input = new Scanner(input);
	}

	public ConsoleUI(PrintStream output, InputStream input) {
		this(output, new InputStreamReader(input));
	}
	
	private void startMainLoop() {
		do {
			printStartMessage();

			restartGame = false;

			do {
				output.print("Enter your guess or command: ");

				String command = input.nextLine();
				
				if ("top".equals(command)) {
					commandProcessor.processCommandTop();
				} else if ("help".equals(command)) {
					commandProcessor.processCommandHelp();
				} else if ("restart".equals(command)) {
					commandProcessor.processCommandRestart();
				} else if ("exit".equals(command)) {
					commandProcessor.processCommandExit();
				} else if (command.matches(String.format("[\\d]{%d}", BullsAndCowsNumber.NUMBER_OF_DIGITS))){
					commandProcessor.processCommandGuess(command);
				} else {
					output.println("Incorrect guess or command!");
				}
			} while(!restartGame && !exitMainLoop);
		} while (!exitMainLoop);
	}

	@Override
	public void start(BullsAndCowsCommandProcessor commandProcessor) {
		if (commandProcessor == null) {
			throw new NullPointerException();
		}

		this.commandProcessor = commandProcessor;
		this.exitMainLoop = false;

		startMainLoop();
	}

	private void printStartMessage() {
		output.println("Welcome to “Bulls and Cows” game. \nPlease try " +
			"to guess my secret 4-digit number. \nUse 'top' to view the " +
			"top scoreboard, 'restart' to start a new game and 'help' " +
			"to cheat and 'exit' to quit the game.");
	}

	@Override
	public void showTopScores(Scoreboard scoreboard) {
		int counter = 0;

		for (ScoreboardEntry current : scoreboard.getEntries()) {			
			if (counter == 0) {
				output.println("Scoreboard:");
			}

			counter++;
			output.println(counter + ". " + current.getName() + " --> " + current.getScore() + " " + 
				(current.getScore() == 1 ? "guess" : "guesses"));
		}

		if (counter == 0) {
			output.println("Top scoreboard is empty.");
		}
	}

	@Override
	public void showCorrectGuessIndication(int numberOfAttempts, int numberOfRevealedDigits) {
		output.print("Congratulations! You guessed the secret number in " + numberOfAttempts + 
			(numberOfAttempts == 1 ? " attempt" : " attempts"));

		if (numberOfRevealedDigits > 0) {
			output.println(" and " + numberOfRevealedDigits + 
				(numberOfRevealedDigits == 1 ? " cheat." : " cheats.") + "\n" +
				"You are not allowed to enter the top scoreboard.");
		} else {
			output.println(".");
		}
	}

	@Override
	public void showWrongGuessIndication(GuessResult guessResult) {
		output.println("Wrong number! Bulls: " + guessResult.getBulls() + 
			", Cows: " + guessResult.getCows());
	}

	@Override
	public String requestNameForScoreboard() {
		output.print("Please enter your name for the top scoreboard: ");

		return input.nextLine();
	}

	@Override
	public void showNotAddedToScoreboardIndication() {
		output.println("Your score is not good enough to enter the scoreboard. Try again.");
	}

	@Override
	public void showHelp(List<Integer> digits, List<Boolean> revealedDigits) {
		StringBuffer hint = new StringBuffer();

		for (int i = 0; i < digits.size(); i++) {
			if (revealedDigits.get(i)) {
				hint.append(digits.get(i));
			} else {
				hint.append("X");
			}
		}

		output.println("The number looks like " + hint + ".");
	}

	@Override
	public void showNoMoreHelpIndication() {
		output.println("No more help is available for this number.");
	}

	@Override
	public void restart() {
		output.println();
		this.restartGame = true;
	}

	@Override
	public void exit() {
		output.println("Good bye!");
		this.exitMainLoop = true;
	}
}

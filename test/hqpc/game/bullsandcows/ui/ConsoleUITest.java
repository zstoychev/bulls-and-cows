package hqpc.game.bullsandcows.ui;

import static org.junit.Assert.*;

import hqpc.game.bullsandcows.controller.BullsAndCowsCommandProcessor;
import hqpc.game.bullsandcows.model.GuessResult;
import hqpc.game.scoreboard.Scoreboard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ConsoleUITest {
	private boolean exitCalled = false;
	private boolean guessCalled = false;
	private boolean helpCalled = false;
	private boolean restartCalled = false;
	private boolean topCalled = false;
	BullsAndCowsCommandProcessor commandProcessor;
	ConsoleUI ui;
	StringReader consoleInput;
	ByteArrayOutputStream consoleOutput;

	private class TestCommandProcessor implements BullsAndCowsCommandProcessor {
		@Override
		public void processCommandExit() {
			exitCalled = true;
			ui.exit();
		}

		@Override
		public void processCommandGuess(String number) {
			guessCalled = true;
		}

		@Override
		public void processCommandHelp() {
			helpCalled = true;
		}

		@Override
		public void processCommandRestart() {
			restartCalled = true;
		}

		@Override
		public void processCommandTop() {
			topCalled = true;
		}		
	}
	
	@Before
	public void setUp() {
		commandProcessor = new TestCommandProcessor();
	}
	
	private void setUpConsoleUI(String input) {
		consoleInput = new StringReader(input);
		consoleOutput = new ByteArrayOutputStream();
		ui = new ConsoleUI(new PrintStream(consoleOutput), consoleInput);
	}
	
	private void testOutput(String output) {
		assertTrue(consoleOutput.toString().replaceAll("\r*\n*", "").equals(output));
	}

	@Test
	public void testCallback() {
		setUpConsoleUI(
				"invalid\n" +
				"4322\n" +
				"help\n" +
				"restart\n" +
				"top\n" +
				"exit\n"
		);

		ui.start(commandProcessor);
		
		assertTrue(consoleOutput.toString().contains("Incorrect guess or command!"));
		assertTrue(guessCalled);
		assertTrue(helpCalled);
		assertTrue(restartCalled);
		assertTrue(topCalled);
		assertTrue(exitCalled);
	}

	@Test
	public void testShowTopScoresEmptyScoreboard() {
		setUpConsoleUI("");
		
		Scoreboard scoreboard = new Scoreboard(3);
		ui.showTopScores(scoreboard);

		testOutput("Top scoreboard is empty.");
	}
	
	@Test
	public void testShowTopScoresNonemptyScoreboard() {
		setUpConsoleUI("");
		
		Scoreboard scoreboard = new Scoreboard(3);
		scoreboard.addScore("Test 1", 1);
		scoreboard.addScore("Test 2", 5);
		scoreboard.addScore("Test 3", 42);

		ui.showTopScores(scoreboard);
		
		testOutput("Scoreboard:1. Test 1 --> 1 guess2. Test 2 --> 5 guesses3. Test 3 --> 42 guesses");
	}

	@Test
	public void testShowCorrectGuessIndicationNonCheated() {
		setUpConsoleUI("");
		
		ui.showCorrectGuessIndication(7, 0);
		
		testOutput("Congratulations! You guessed the secret number in 7 attempts.");
	}
	
	@Test
	public void testShowCorrectGuessIndicationCheated() {
		setUpConsoleUI("");
		
		ui.showCorrectGuessIndication(7, 1);

		testOutput("Congratulations! You guessed the secret number in 7 attempts and 1 cheat." +
			"You are not allowed to enter the top scoreboard.");
	}

	@Test
	public void testShowWrongGuessIndication() {
		setUpConsoleUI("");
		
		ui.showWrongGuessIndication(new GuessResult(3, 1));
		
		testOutput("Wrong number! Bulls: 3, Cows: 1");
	}

	@Test
	public void testRequestNameForScoreboard() {
		setUpConsoleUI("Test\n");
		
		String name = ui.requestNameForScoreboard();
		
		assertEquals("Test", name);
		
		testOutput("Please enter your name for the top scoreboard: ");
	}

	@Test
	public void testShowNotAddedToScoreboardIndication() {
		setUpConsoleUI("");
		
		ui.showNotAddedToScoreboardIndication();

		testOutput("Your score is not good enough to enter the scoreboard. Try again.");
	}

	@Test
	public void testShowHelpIndicationAllUnrevealed() {
		setUpConsoleUI("");
		
		List<Integer> digits = Arrays.asList(new Integer[] {
				1, 2, 3, 4
		});
		List<Boolean> revealedDigits = Arrays.asList(new Boolean[] {
				false, false, false, false
		});
		
		ui.showHelp(digits, revealedDigits);
		
		testOutput("The number looks like XXXX.");
	}
	
	@Test
	public void testShowHelpIndicationSomeRevealed() {
		setUpConsoleUI("");
		
		List<Integer> digits = Arrays.asList(new Integer[] {
				1, 2, 3, 4
		});
		List<Boolean> revealedDigits = Arrays.asList(new Boolean[] {
				true, false, true, false
		});
		
		ui.showHelp(digits, revealedDigits);
		
		testOutput("The number looks like 1X3X.");
	}
	
	@Test
	public void testShowHelpIndicationAllrevealed() {
		setUpConsoleUI("");
		
		List<Integer> digits = Arrays.asList(new Integer[] {
				0, 2, 3, 4
		});
		List<Boolean> revealedDigits = Arrays.asList(new Boolean[] {
				true, true, true, true
		});
		
		ui.showHelp(digits, revealedDigits);
		
		testOutput("The number looks like 0234.");
	}
	
	@Test
	public void testShowNoMoreHelpIndication() {
		setUpConsoleUI("Test\n");
		
		ui.showNoMoreHelpIndication();
		
		testOutput("No more help is available for this number.");
	}

	@Test
	public void testRestart() {
		setUpConsoleUI("");
		
		ui.restart();

		testOutput("");
	}

	@Test
	public void testExit() {
		setUpConsoleUI("");
		
		ui.exit();

		testOutput("Good bye!");
	}

}

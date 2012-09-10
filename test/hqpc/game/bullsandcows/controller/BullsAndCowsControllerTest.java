package hqpc.game.bullsandcows.controller;

import static org.junit.Assert.*;

import java.util.List;

import hqpc.game.bullsandcows.model.BullsAndCowsNumber;
import hqpc.game.bullsandcows.model.GameState;
import hqpc.game.bullsandcows.model.GuessResult;
import hqpc.game.bullsandcows.ui.BullsAndCowsUI;
import hqpc.game.scoreboard.Scoreboard;

import org.junit.Before;
import org.junit.Test;


public class BullsAndCowsControllerTest {
	private	boolean calledExit;
	private	boolean calledRequestNameForScoreboard;
	private	boolean calledRestart;
	private	boolean calledShowCorrectGuessIndication;
	private	boolean calledShowHelp;
	private	boolean calledShowNoMoreHelpIndication;
	private	boolean calledShowNotAddedToScoreboardIndication;
	private	boolean calledShowTopScores;
	private	boolean calledShowWrongGuessIndication;
	private	boolean calledStart;
	private BullsAndCowsUI ui;
	private BullsAndCowsController controller;
	private BullsAndCowsCommandProcessor commandProcessor;
	
	private class TestUI implements BullsAndCowsUI {
		@Override
		public void exit() {
			calledExit = true;
		}

		@Override
		public String requestNameForScoreboard() {
			calledRequestNameForScoreboard = true;
			
			return "";
		}

		@Override
		public void restart() {
			calledRestart = true;
		}

		@Override
		public void showCorrectGuessIndication(int numberOfAttempts,
				int numberOfRevealedDigits) {
			calledShowCorrectGuessIndication = true;
		}

		@Override
		public void showHelp(List<Integer> digits, List<Boolean> revealedDigits) {
			calledShowHelp = true;
		}

		@Override
		public void showNoMoreHelpIndication() {
			calledShowNoMoreHelpIndication = true;
		}

		@Override
		public void showNotAddedToScoreboardIndication() {
			calledShowNotAddedToScoreboardIndication = true;
		}

		@Override
		public void showTopScores(Scoreboard scoreboard) {
			calledShowTopScores = true;
		}

		@Override
		public void showWrongGuessIndication(GuessResult guessResult) {
			calledShowWrongGuessIndication = true;
		}

		@Override
		public void start(BullsAndCowsCommandProcessor commandProcessor) {
			calledStart = true;
			
			BullsAndCowsControllerTest.this.commandProcessor = commandProcessor;
		}
	}
	
	@Before
	public void setUp() {
		calledExit = false;
		calledRequestNameForScoreboard = false;
		calledRestart = false;
		calledShowCorrectGuessIndication = false;
		calledShowHelp = false;
		calledShowNoMoreHelpIndication = false;
		calledShowNotAddedToScoreboardIndication = false;
		calledShowTopScores = false;
		calledShowWrongGuessIndication = false;
		calledStart = false;
		ui = new TestUI();
	}
	
	public void testStart() {
		controller = new BullsAndCowsController(ui);
		controller.start();
		
		assertTrue(calledStart);
	}
	
	@Test
	public void testTop() {
		controller = new BullsAndCowsController(ui);
		controller.start();
		
		commandProcessor.processCommandTop();
		
		assertTrue(calledShowTopScores);
	}
	
	@Test
	public void testHelp() {
		GameState gameState = new GameState(new BullsAndCowsNumber());
		controller = new BullsAndCowsController(ui, new Scoreboard(5), gameState);
		controller.start();
		
		commandProcessor.processCommandHelp();
		
		assertTrue(calledShowHelp);
		assertTrue(gameState.getNumberOfRevealedDigits() == 1);
		
		
		for (int i = 0; i < BullsAndCowsNumber.NUMBER_OF_DIGITS - 1; i++) {
			gameState.revealRandomDigit();
		}
		commandProcessor.processCommandHelp();
		
		assertTrue(calledShowNoMoreHelpIndication);
	}
	
	@Test
	public void testRestart() {
		controller = new BullsAndCowsController(ui);
		controller.start();
		
		commandProcessor.processCommandRestart();
		
		assertTrue(calledRestart);
	}
	
	@Test
	public void testExit() {
		controller = new BullsAndCowsController(ui);
		controller.start();
		
		commandProcessor.processCommandExit();
		
		assertTrue(calledExit);
	}
	
	@Test
	public void testWrongGuess() {
		GameState gameState = new GameState(new BullsAndCowsNumber(1234));
		controller = new BullsAndCowsController(ui, new Scoreboard(5), gameState);
		controller.start();
		
		commandProcessor.processCommandGuess("1233");
		
		assertTrue(calledShowWrongGuessIndication);
	}
	
	@Test
	public void testTopRightGuess() {
		GameState gameState = new GameState(new BullsAndCowsNumber(1234));
		controller = new BullsAndCowsController(ui, new Scoreboard(5), gameState);
		controller.start();
		
		commandProcessor.processCommandGuess("1234");
		
		assertTrue(calledShowCorrectGuessIndication);
		assertTrue(calledRequestNameForScoreboard);
		assertTrue(calledShowTopScores);
		assertTrue(calledRestart);
	}
	
	@Test
	public void testNotTopRightGuess() {
		GameState gameState = new GameState(new BullsAndCowsNumber(1234));
		Scoreboard scoreboard = new Scoreboard(1);
		controller = new BullsAndCowsController(ui, scoreboard, gameState);
		controller.start();
		
		scoreboard.addScore("Test", 1);
		
		commandProcessor.processCommandGuess("1234");
		
		assertTrue(calledShowCorrectGuessIndication);
		assertTrue(calledShowNotAddedToScoreboardIndication);
		assertTrue(calledShowTopScores);
		assertTrue(calledRestart);
	}
}

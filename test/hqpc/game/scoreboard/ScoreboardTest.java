package hqpc.game.scoreboard;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreboardTest {
	@Test
	public void testIsTopScoreNotFullScoreboard() {
		Scoreboard scoreboard = new Scoreboard(5);
		
		assertTrue(scoreboard.isTopScore(42));

		scoreboard.addScore("John", 42);
		
		assertTrue(scoreboard.isTopScore(42));
		assertTrue(scoreboard.isTopScore(45));		
	}
	
	private static void fillScoreboard(Scoreboard scoreboard) {
		scoreboard.addScore("Test1", 1);
		scoreboard.addScore("Test1", 2);
		scoreboard.addScore("Test1", 2);
		scoreboard.addScore("Test1", 4);
		scoreboard.addScore("Test1", 4);
	}
	
	@Test
	public void testIsTopScoreFullScoreboardAndLowerScore() {
		Scoreboard scoreboard = new Scoreboard(5);
		
		fillScoreboard(scoreboard);
		
		assertFalse(scoreboard.isTopScore(5));
	}
	
	@Test
	public void testIsTopScoreFullScoreboardAndGreaterScore() {
		Scoreboard scoreboard = new Scoreboard(5);
		
		fillScoreboard(scoreboard);
		
		assertTrue(scoreboard.isTopScore(3));
	}

	@Test
	public void testAddScore() {
		Scoreboard scoreboard = new Scoreboard(2);
		
		assertTrue(scoreboard.getEntries().isEmpty());
		
		scoreboard.addScore("Test1", 2);
		assertTrue(scoreboard.getEntries().size() == 1);
		assertEquals(scoreboard.getEntries().first().getName(), "Test1");
		assertEquals(scoreboard.getEntries().first().getScore(), 2);
		
		scoreboard.addScore("Test2", 3);
		assertTrue(scoreboard.getEntries().size() == 2);
		assertEquals(scoreboard.getEntries().last().getName(), "Test2");
		assertEquals(scoreboard.getEntries().last().getScore(), 3);
		
		scoreboard.addScore("Test3", 1);
		assertTrue(scoreboard.getEntries().size() == 2);
		assertEquals(scoreboard.getEntries().first().getName(), "Test3");
		assertEquals(scoreboard.getEntries().first().getScore(), 1);
		assertEquals(scoreboard.getEntries().last().getName(), "Test1");
		assertEquals(scoreboard.getEntries().last().getScore(), 2);
	}
}

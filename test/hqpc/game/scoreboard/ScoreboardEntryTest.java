package hqpc.game.scoreboard;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ScoreboardEntryTest {
	@Test
	public void testHashCode() {
		ScoreboardEntry firstEntry = new ScoreboardEntry(42, "John", 9);
		ScoreboardEntry secondEntry = new ScoreboardEntry(42, "John", 9);
		
		assertEquals(firstEntry.hashCode(), secondEntry.hashCode());
	}

	@Test
	public void testEqualsObject() {
		ScoreboardEntry firstEntry = new ScoreboardEntry(42, "John", 9);
		ScoreboardEntry secondEntry = new ScoreboardEntry(42, "John", 9);
		ScoreboardEntry thirdEntry = new ScoreboardEntry(42, "Josh", 9);
		
		assertFalse(firstEntry.equals(null));
		
		assertTrue(firstEntry.equals(secondEntry));
		assertTrue(secondEntry.equals(firstEntry));
		assertFalse(firstEntry.equals(thirdEntry));
		assertFalse(thirdEntry.equals(firstEntry));
	}

	@Test
	public void testGettersTest() {
		ScoreboardEntry entry = new ScoreboardEntry(42, "John", 9);
		
		assertEquals(42, entry.getId());
		assertEquals("John", entry.getName());
		assertEquals(9, entry.getScore());
	}

	@Test
	public void testGetByScoreComparator() {
		Comparator<ScoreboardEntry> byScoreComparator = ScoreboardEntry.getByScoreComparator();
		
		ScoreboardEntry firstEntry = new ScoreboardEntry(42, "John", 9);
		ScoreboardEntry secondEntry = new ScoreboardEntry(42, "John", 9);
		ScoreboardEntry thirdEntry = new ScoreboardEntry(43, "Josh", 9);
		ScoreboardEntry fourthEntry = new ScoreboardEntry(43, "Josh", 10);
		
		assertTrue(byScoreComparator.compare(firstEntry, secondEntry) == 0);
		assertTrue(Math.signum(byScoreComparator.compare(firstEntry, thirdEntry)) == 
			-Math.signum(byScoreComparator.compare(thirdEntry, firstEntry)));
		assertTrue(Math.signum(byScoreComparator.compare(firstEntry, fourthEntry)) == 
			-Math.signum(byScoreComparator.compare(fourthEntry, firstEntry)));
		assertTrue(byScoreComparator.compare(fourthEntry, thirdEntry) > 0 &&
				byScoreComparator.compare(thirdEntry, firstEntry) > 0 &&
				byScoreComparator.compare(fourthEntry, firstEntry) > 0);
		assertTrue(Math.signum(byScoreComparator.compare(firstEntry, thirdEntry)) ==
			Math.signum(byScoreComparator.compare(secondEntry, thirdEntry)));
	}
	
}

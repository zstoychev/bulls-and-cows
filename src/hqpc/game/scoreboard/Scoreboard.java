package hqpc.game.scoreboard;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Scoreboard {
	private SortedSet<ScoreboardEntry> entries;
	private int maxNumberOfEntries;
	private int nextEntryID;
	
	public Scoreboard(int maxNumberOfEntries) {
		if (maxNumberOfEntries <= 0) {
			throw new IllegalArgumentException();
		}

		this.entries = new TreeSet<ScoreboardEntry>(ScoreboardEntry.getByScoreComparator());
		this.maxNumberOfEntries = maxNumberOfEntries;
		this.nextEntryID = 0;
	}
	
	public boolean isTopScore(int score) {
		return entries.size() < this.maxNumberOfEntries || score < entries.last().getScore();
	}
	
	public void addScore(String name, int score) {
		if (name == null) {
			throw new NullPointerException();
		}

		entries.add(new ScoreboardEntry(nextEntryID++, name, score));
		
		if (entries.size() > maxNumberOfEntries) {
			entries.remove(entries.last());
		}
	}
	
	public SortedSet<ScoreboardEntry> getEntries() {
		return Collections.unmodifiableSortedSet(entries);
	}
}

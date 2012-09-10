package hqpc.game.scoreboard;

import java.util.Comparator;

public final class ScoreboardEntry {
	private final int id;
	private final String name;
	private final int score;
	
	public ScoreboardEntry(int id, String name, int score) {
		if (name == null) {
			throw new NullPointerException();
		}

		this.id = id;
		this.name = name;
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
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
		ScoreboardEntry other = (ScoreboardEntry) obj;

		return id == other.id && name.equals(other.name) && score == other.score;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	
	public static Comparator<ScoreboardEntry> getByScoreComparator() {
		return new Comparator<ScoreboardEntry>() {
			@Override
			public int compare(ScoreboardEntry entry1, ScoreboardEntry entry2) {
				if (entry1.getScore() < entry2.getScore()) {
					return -1;
				} else if (entry1.getScore() > entry2.getScore()) {
					return 1;
				} else if (entry1.getId() < entry2.getId()) {
					return -1;
				} else if (entry1.getId() > entry2.getId()) {
					return 1;
				} else {
					return 0;
				}
			}
		};
	}
}

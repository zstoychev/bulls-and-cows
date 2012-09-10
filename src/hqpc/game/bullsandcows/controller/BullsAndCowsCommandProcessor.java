package hqpc.game.bullsandcows.controller;

public interface BullsAndCowsCommandProcessor {
	void processCommandTop();
	void processCommandHelp();
	void processCommandRestart();
	void processCommandExit();
	void processCommandGuess(String number);
}

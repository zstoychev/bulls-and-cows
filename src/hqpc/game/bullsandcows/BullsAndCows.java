package hqpc.game.bullsandcows;

import hqpc.game.bullsandcows.controller.BullsAndCowsController;
import hqpc.game.bullsandcows.ui.ConsoleUI;

public class BullsAndCows {	
	public static void main(String[] args) {
		new BullsAndCowsController(new ConsoleUI(System.out, System.in)).start();
	}
}

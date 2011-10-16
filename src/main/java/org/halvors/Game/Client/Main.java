package main.java.org.halvors.Game.Client;

public class Main {
	public static void main(String[] args) {
		try {
			// Start the game.
			Game game = new Game();
			game.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

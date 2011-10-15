package main.java.org.halvors.Game.Client;

public class Main {
	public static void main(String[] args) {
		try {
			// Start the client.
			Game client = new Game();
			client.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

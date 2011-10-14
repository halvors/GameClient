package main.java.org.halvors.Game.Server;

public class Main {
	public static void main(String[] args) {
		try {
			// Start the server instance.
			Server server = new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

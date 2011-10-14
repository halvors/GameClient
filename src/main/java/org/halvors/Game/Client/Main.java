package main.java.org.halvors.Game.Client;

public class Main {
	public static void main(String[] args) {
		try {
			// Start the client.
			Client client = new Client();
			client.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

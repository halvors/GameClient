package main.java.org.halvors.Game.Client;

import java.io.IOException;
import java.util.logging.Logger;

public class Client {
	private static Client instance;
	
	private final Logger logger = Logger.getLogger("Game");
	
	public Client() {
		Client.instance = this;

		try {
		NetworkManager nm = new NetworkManager("127.0.0.1", 7846);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Client getInstance() {
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}
}

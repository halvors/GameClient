package main.java.org.halvors.Game.Client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private static Client instance;
	private final NetworkManager networkManager = new NetworkManager();
	
	private final Logger logger = Logger.getLogger("Game");
	
	public Client() {
		Client.instance = this;		
	}
	
	public void main(String[] args) {
		try {
			networkManager.connect("127.0.0.1", 7846);
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
	
	public void log(Level level, String message) {
		logger.log(level, message);
	}
}

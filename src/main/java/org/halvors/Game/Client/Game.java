package main.java.org.halvors.Game.Client;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Client.gui.MainWindow;

public class Game {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.1";
	
	private final Logger logger = Logger.getLogger("Game");
	
	public Game() {
		Game.instance = this;	
	}
	
	public void main(String[] args) {
		// Create the menu.
		MainWindow mainWindow = new MainWindow(this);
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void log(Level level, String message) {
		logger.log(level, message);
	}
}

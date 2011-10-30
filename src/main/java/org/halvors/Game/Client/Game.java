package org.halvors.Game.Client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.halvors.Game.Client.gui.MainWindow;
import org.halvors.Game.Client.network.NetworkManager;
import org.lwjgl.LWJGLException;

public class Game {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.2";
	
	private final Logger logger = Logger.getLogger("Game");
	
	private NetworkManager networkManager;
	
	public Game() {
		Game.instance = this;	
	}
	
	public void main(String[] args) throws LWJGLException {
		MainWindow mainWindow = new MainWindow(this);
		
//		Display.setDisplayMode(new DisplayMode(800, 600));
//		Display.setTitle(name + " " + version);
//		Display.create();
//		
//		while (!Display.isCloseRequested()) {
//			// TODO: Render OpenGL here
//			Display.update();
//		}
//		
//		Display.destroy();
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

	public NetworkManager setNetworkManager(NetworkManager networkManager) {
		this.networkManager = networkManager;
		
		return networkManager;
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
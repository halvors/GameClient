package main.java.org.halvors.Game.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Client.World;
import main.java.org.halvors.Game.Client.entity.Player;

public class Server {
	private static Server instance;
	
	private final Logger logger = Logger.getLogger("Game");
	private final NetworkManager networkManager = new NetworkManager(this);
	
	private final List<World> worlds = new ArrayList<World>();
	private final List<Player> players = new ArrayList<Player>();
	
	public Server() throws IOException {
		Server.instance = this;
		
		// Creates the NetworkManager.
		networkManager.listen(7846);
	}

	public static Server getInstance() {
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void log(Level level, String message) {
		logger.log(level, message);
	}
	
	public List<World> getWorlds() {
		return worlds;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
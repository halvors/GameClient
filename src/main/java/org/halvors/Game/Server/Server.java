package main.java.org.halvors.Game.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Server.entity.Player;

public class Server {
	private static Server instance;
	
	private final Logger logger = Logger.getLogger("Game");
//	private final Configuration configuration = new Configuration();
	private final NetworkManager networkManager = new NetworkManager(this);
	
	private final List<World> worlds = new ArrayList<World>();
	private final List<Player> players = new ArrayList<Player>();
	
	public Server() {
		Server.instance = this;
	}
	
	public void main(String[] args) {
		// Create the NetworkManager.
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
	
	public World getWorld(UUID id) {
		for (World world : worlds) {
			if (id.equals(world.getId())) {
				return world;
			}
		}
		
		return null;
	}
	
	public World getWorld(String name) {
		for (World world : worlds) {
			if (name.equals(world.getName())) {
				return world;
			}
		}
		
		return null;
	}
	
	public World createWorld(String name) {
		World world = new World(name);
		
		return world;
	}
	
	public void removeWorld(UUID id) {
		World world = getWorld(id);
		
		if (world != null) {
			worlds.remove(world);
		}
	}
	
	public void removeWorld(String name) {
		World world = getWorld(name);
		
		if (world != null) {
			worlds.remove(world);
		}
	} 
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(String name) {
		for (Player player : players) {
			if (name.equalsIgnoreCase(player.getName())) {
				return player;
			}
		}
		
		return null;
	}
	
	public void addPlayer(Player player) {
		if (player != null && !players.contains(player)) {
			players.add(player);
		}
	}
	
	public void removePlayer(Player player) {
		if (players.contains(player)) {
			players.remove(player);
		}
	}

//	public Configuration getConfiguration() {
//		return configuration;
//	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
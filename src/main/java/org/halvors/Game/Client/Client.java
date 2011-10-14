package main.java.org.halvors.Game.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Client.entity.Player;

public class Client {
	private static Client instance;
	
	private final Logger logger = Logger.getLogger("Game");
	private final List<World> worlds = new ArrayList<World>();
	private final List<Player> players = new ArrayList<Player>();
	
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
	
	public List<World> getWorlds() {
		return worlds;
	}
	
	public World createWorld(String name) {
		World world = new World("world");
		
		return world;
	}
	
	public World createWorld() {
		 return createWorld("world"); // TODO: Make this function make world with different names, such as: world, world1, world2.
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(String name) {
		for (Player p : players) {
			if (name.equalsIgnoreCase(p.getName())) {
				return p;
			}
		}
		
		return null;
	}
}

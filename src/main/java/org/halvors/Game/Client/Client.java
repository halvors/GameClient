package main.java.org.halvors.Game.Client;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.org.halvors.Game.Client.entity.Player;

public class Client {
	private static Client instance;
	
	private final Logger logger = Logger.getLogger("Game");
	private final List<World> worlds = new ArrayList<World>();
	private final List<Player> players = new ArrayList<Player>();
	
	public Client() {
		Client.instance = this;
		
		Scanner in = new Scanner(System.in);
		String host;
		int port;
		
		logger.log(Level.INFO, "Enter a host: ");
		host = in.nextLine();
		logger.log(Level.INFO, "Enter a port: ");
		port = in.nextInt();
		
		try {
			Socket socket = new Socket(host, port);
			logger.log(Level.INFO, "Connected to: " + host + ":" + port);
		} catch (Exception e){
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

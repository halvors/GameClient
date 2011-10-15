package main.java.org.halvors.Game.Server.entity;

import main.java.org.halvors.Game.Server.Location;

public class Player extends LivingEntity {
	private final String name;
//	private final NetworkServerHandler networkServerHandler;
	
	public Player(String name, Location location) {
		super(location);
		this.name = name;
//		this.networkServerHandler = new NetworkServerHandler();
	}
	
	public String getName() {
		return name;
	}
}

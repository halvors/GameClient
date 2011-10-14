package main.java.org.halvors.Game.Client.entity;

import java.net.InetAddress;

import main.java.org.halvors.Game.Client.Location;

public class Player extends LivingEntity {
	private final String name;
	private final InetAddress address;
	
	public Player(String name, InetAddress address, Location location) {
		super(location);
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public InetAddress getAddress() {
		return address;
	}
}

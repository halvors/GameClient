package main.java.org.halvors.Game.Client.entity;

import java.util.UUID;

import main.java.org.halvors.Game.Client.Location;
import main.java.org.halvors.Game.Client.World;

public class Entity {
	private final World world;
	private final UUID id = UUID.randomUUID();
	private Location location;
	
	public Entity(Location location) {
		this.world = location.getWorld();
		setLocation(location);
	}

	public World getWorld() {
		return world;
	}
	
	public UUID getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}

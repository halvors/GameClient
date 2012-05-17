package org.halvors.Game.Client.entity;

import java.util.UUID;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.world.Location;
import org.halvors.Game.Client.world.World;

public class Entity implements IEntity {
	private final Game client;
	private final UUID id;
	
	private Location location;
	
	public Entity(Game client, UUID id, Location loc) {
		this.client = client;
		this.id = id;
		
		setLocation(loc);
	}
	
	public Entity(Game client, Location loc) {
		this(client, UUID.randomUUID(), loc);
	}
	
	public Game getClient() {
		return client;
	}
	
	public UUID getId() {
		return id;
	}
	
	public World getWorld() {
		return location.getWorld();
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void remove() {
		getWorld().removeEntity(this);
	}
}

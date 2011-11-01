package org.halvors.Game.Client.entity;

import java.util.UUID;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.World;

public class Entity {
	private final Game client;
	private final UUID id;
	
	private World world;
	private Location location;
	
	public Entity(Game client, UUID id, Location loc) {
		this.client = client;
		this.id = id;
		setWorld(loc.getWorld());
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
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void remove() {
		world.removeEntity(this);
	}
}

package org.halvors.Game.Client.entity;

import java.util.UUID;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.World;

public interface IEntity {
	public Game getClient();
	
	public UUID getId();
	
	public World getWorld();
	
	public Location getLocation();

	public void setLocation(Location location);
	
	public void remove();
}

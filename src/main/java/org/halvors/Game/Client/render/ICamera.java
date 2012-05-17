package org.halvors.Game.Client.render;

import org.halvors.Game.Client.world.Location;

public interface ICamera {
	public Location getLocation();
	
	public void setLocation(Location loc);
}
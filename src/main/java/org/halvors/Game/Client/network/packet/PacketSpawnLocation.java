package org.halvors.Game.Client.network.packet;

import org.halvors.Game.Client.Location;

public class PacketSpawnLocation extends PacketLocation {
	public PacketSpawnLocation() {
		
	}
	
	public PacketSpawnLocation(Location loc) {
		super(loc);
	}
}

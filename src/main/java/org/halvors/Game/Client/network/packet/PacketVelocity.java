package org.halvors.Game.Client.network.packet;

import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Player;

public class PacketVelocity extends PacketLocation {
	public PacketVelocity() {
		
	}
	
	public PacketVelocity(Location loc) {
		super(loc);
	}
	
	@Override
	public void run(Player player) {
		
	}
}

package org.halvors.Game.Client.network.packet;

import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Player;

public class PacketEntityVelocity extends PacketLocation {
	public PacketEntityVelocity() {
		
	}
	
	public PacketEntityVelocity(Location loc) {
		super(loc);
	}
	
	@Override
	public void run(Player player) {
		
	}
}

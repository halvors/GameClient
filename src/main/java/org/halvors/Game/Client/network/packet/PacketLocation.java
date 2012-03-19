package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.World;

public class PacketLocation extends Packet {
	private final Game client = Game.getInstance();
	private Location loc;
	
	public PacketLocation() {
		
	}
	
	public PacketLocation(Location loc) {
		setLocation(loc);
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		UUID id = UUID.fromString(input.readUTF());
		float x = input.readFloat();
		float y = input.readFloat();
		float z = input.readFloat();
		float pitch = input.readFloat();
		float yaw = input.readFloat();
		
		World world = client.getWorld(id);
		Location loc = new Location(world, x, y, z, pitch, yaw);
		setLocation(loc);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		World world = loc.getWorld();
		
		output.writeUTF(world.getId().toString());
		output.writeFloat(loc.getX());
		output.writeFloat(loc.getY());
		output.writeFloat(loc.getZ());
		output.writeFloat(loc.getPitch());
		output.writeFloat(loc.getYaw());
	}
	
	@Override
	public int getSize() {
		return 0;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
}

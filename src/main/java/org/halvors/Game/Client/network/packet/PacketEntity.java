package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.entity.Entity;
import org.halvors.Game.Client.entity.Player;

public class PacketEntity extends PacketLocation {
	private final Game client = Game.getInstance();
	
	private Entity entity;
	
	public PacketEntity() {
		
	}
	
	public PacketEntity(Entity entity) {
		super(entity.getLocation());
		setEntity(entity);
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		UUID id = UUID.fromString(input.readUTF());
		super.readData(input);
		
		// Recreate the objects based on the read info.
		Entity entity = new Entity(client, id, super.getLocation());
		
		setEntity(entity);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeUTF(entity.getId().toString());
		super.writeData(output);
	}
	
	@Override
	public void run(Player player) {
		
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}

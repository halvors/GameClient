package org.halvors.Game.Client.network;

import java.io.IOException;
import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.World;
import org.halvors.Game.Client.entity.Entity;
import org.halvors.Game.Client.gui.Chat;
import org.halvors.Game.Client.network.packet.PacketChat;
import org.halvors.Game.Client.network.packet.PacketDisconnect;
import org.halvors.Game.Client.network.packet.PacketEntity;
import org.halvors.Game.Client.network.packet.PacketLogin;
import org.halvors.Game.Client.network.packet.PacketSpawnLocation;
import org.halvors.Game.Client.network.packet.PacketWorld;
import org.halvors.Game.Client.render.RenderEntity;

public class ClientHandler {
	private final Game client;
	private final NetworkManager networkManager;
	
	private Chat chat;
	
	public ClientHandler(Game client, NetworkManager networkManager) {
		this.client = client;
		this.networkManager = networkManager;
	}
	
	public void handlePacketLogin(PacketLogin packet) {
		chat = new Chat(client);
		
		client.log(Level.INFO, "Succesfully logged in.");
	}
	
	public void handlePacketChat(PacketChat packet) {
		chat.showMessage(packet.getMessage());
		
		client.log(Level.INFO, packet.getMessage());
	}
	
	public void handlePacketWorld(PacketWorld packet) {
		World world = packet.getWorld();
		
		// Register the world.
		if (!client.hasWorld(world)) {
			client.addWorld(world);
		}
	}
	
	public void handlePacketEntity(PacketEntity packet) {
		Entity entity = packet.getEntity();
		World world = entity.getWorld();
		
		// Register the entity.
		world.addEntity(entity);
		
		// Render entities.
		RenderEntity renderEntity = new RenderEntity(client);
		renderEntity.render(entity);
	}

	public void handlePacketSpawnLocation(PacketSpawnLocation packet) {
		
	}
	
	public void handlePacketDisconnect(PacketDisconnect packet) throws IOException {
		networkManager.close();
		
		client.log(Level.INFO, "Disconnected: " + packet.getReason());
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
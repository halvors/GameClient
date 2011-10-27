package org.halvors.Game.Client.network;

import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.PacketChat;
import org.halvors.Game.Client.network.packet.PacketDisconnect;
import org.halvors.Game.Client.network.packet.PacketLogin;

public class NetworkClientHandler {
	private final Game client;
	private final NetworkManager networkManager;
	
	public NetworkClientHandler(Game client, NetworkManager networkManager) {
		this.client = client;
		this.networkManager = networkManager;
	}
	
	public void handlePacketLogin(PacketLogin packet) {
		client.log(Level.INFO, "Succesfully logged in.");
	}
	
	public void handlePacketChat(PacketChat packet) {
		client.log(Level.INFO, packet.getMessage());
	}
	
	public void handlePacketDisconnect(PacketDisconnect packet) {
		
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
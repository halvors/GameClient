package org.halvors.Game.Client.network;

import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.gui.Chat;
import org.halvors.Game.Client.network.packet.PacketChat;
import org.halvors.Game.Client.network.packet.PacketDisconnect;
import org.halvors.Game.Client.network.packet.PacketLogin;

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
		String message = packet.getMessage();
		
		chat.showMessage(message);
		client.log(Level.INFO, message);
	}
	
	public void handlePacketDisconnect(PacketDisconnect packet) {
//		try {
//			networkManager.shutdown();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		client.log(Level.INFO, "Disconnected: " + packet.getReason());
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
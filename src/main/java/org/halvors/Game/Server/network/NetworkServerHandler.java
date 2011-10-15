package main.java.org.halvors.Game.Server.network;

import main.java.org.halvors.Game.Server.GameServer;
import main.java.org.halvors.Game.Server.entity.Player;
import main.java.org.halvors.Game.Server.network.packet.PacketChat;
import main.java.org.halvors.Game.Server.network.packet.PacketConnect;
import main.java.org.halvors.Game.Server.network.packet.PacketDisconnect;

public class NetworkServerHandler {;
	private final GameServer server;
	private final NetworkManager networkManager;
	private final Player player;
	
	public NetworkServerHandler(GameServer server, NetworkManager networkManager, Player player) {
		this.server = server;
		this.networkManager = networkManager;
		this.player = player;
	}
	
	public void handlePacketConnect(PacketConnect packet) {
		server.addPlayer(player);
		
		System.out.println(player.getName() + " joined the game.");
	}
	
	public void handlePacketChat(PacketChat packet) {
		System.out.println(player.getName() + ": " + packet.message);
	}
	
	public void handlePacketDisconnect(PacketDisconnect packet) {
		// Remove the player from the server.
		server.removePlayer(player);
		
		System.out.println(player.getName() + " left the game.");
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}

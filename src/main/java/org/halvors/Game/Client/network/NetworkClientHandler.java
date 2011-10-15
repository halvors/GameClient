package main.java.org.halvors.Game.Client.network;

import main.java.org.halvors.Game.Client.Game;
import main.java.org.halvors.Game.Client.network.packet.PacketChat;
import main.java.org.halvors.Game.Client.network.packet.PacketLogin;
import main.java.org.halvors.Game.Client.network.packet.PacketDisconnect;

public class NetworkClientHandler {
//	private final Game client;
	private final NetworkManager networkManager;
	
	public NetworkClientHandler(Game client, NetworkManager networkManager) {
//		this.client = client;
		this.networkManager = networkManager;
	}
	
	public void handlePacketLogin(PacketLogin packet) {
		
	}
	
	public void handlePacketChat(PacketChat packet) {
		
	}
	
	public void handlePacketDisconnect(PacketDisconnect packet) {
		
	}

	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
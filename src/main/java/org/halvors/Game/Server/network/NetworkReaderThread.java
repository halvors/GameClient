package main.java.org.halvors.Game.Server.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import main.java.org.halvors.Game.Server.GameServer;
import main.java.org.halvors.Game.Server.network.packet.Packet;
import main.java.org.halvors.Game.Server.network.packet.PacketUtil;

public class NetworkReaderThread extends Thread {
	private final GameServer server = GameServer.getInstance();
	private final NetworkManager networkManager;
	private final Socket socket;
	
	public NetworkReaderThread(NetworkManager networkManager) {
		this.networkManager = networkManager;
		this.socket = networkManager.getSocket();
	}
	
	public void run() {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			
			while (socket.isConnected()) {
				Packet packet = PacketUtil.readPacket(input);
				packet.handlePacket(packet, networkManager.getNetworkServerHandler());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
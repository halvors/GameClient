package main.java.org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import main.java.org.halvors.Game.Client.network.packet.Packet;
import main.java.org.halvors.Game.Client.network.packet.PacketUtil;

public class NetworkReaderThread extends Thread {
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
			
				if (packet != null && input != null) {
					packet.handlePacket(packet, networkManager.getNetworkClientHandler());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}

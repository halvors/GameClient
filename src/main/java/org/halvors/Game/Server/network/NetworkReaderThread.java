package main.java.org.halvors.Game.Server.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import main.java.org.halvors.Game.Server.network.packet.Packet;
import main.java.org.halvors.Game.Server.network.packet.PacketLogin;
import main.java.org.halvors.Game.Server.network.packet.PacketUtil;

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
			Packet packet = null;
			
			while (socket.isConnected()) {
				packet = PacketUtil.readPacket(input);
			
				if (packet != null && input != null) {
					if (packet instanceof PacketLogin) {
						networkManager.getLoginHandler().handleLogin((PacketLogin) packet);
					} else {
						packet.handlePacket(packet, networkManager.getNetworkServerHandler());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
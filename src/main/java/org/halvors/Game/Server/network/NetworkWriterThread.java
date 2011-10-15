package main.java.org.halvors.Game.Server.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.java.org.halvors.Game.Server.network.packet.Packet;
import main.java.org.halvors.Game.Server.network.packet.PacketUtil;

public class NetworkWriterThread extends Thread {
	private final NetworkManager networkManager;
	private final Socket socket;
	
	public NetworkWriterThread(NetworkManager networkManager) {
		this.networkManager = networkManager;
		this.socket = networkManager.getSocket();
	}
	
	public void run() {
		try {
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			
			while (socket.isConnected()) {
				Packet current = networkManager.getPacketQueue().poll();
				
				if (current != null) {
					PacketUtil.writePacket(current, output);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

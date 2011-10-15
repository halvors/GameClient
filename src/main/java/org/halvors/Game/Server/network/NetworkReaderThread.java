package main.java.org.halvors.Game.Server.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import main.java.org.halvors.Game.Server.GameServer;
import main.java.org.halvors.Game.Server.network.packet.Packet;
import main.java.org.halvors.Game.Server.network.packet.PacketChat;
import main.java.org.halvors.Game.Server.network.packet.PacketUtil;

public class NetworkReaderThread extends Thread {
	private final GameServer server = GameServer.getInstance();
	private final Socket socket;
	
	public NetworkReaderThread(NetworkManager networkManager) {
		this.socket = networkManager.getSocket();
	}
	
	public void run() {
		while (socket.isConnected()) {
			if (server != null) {
				try {
					DataInputStream input = new DataInputStream(socket.getInputStream());
					Packet packet = PacketUtil.readPacket(input);
					
					if (packet instanceof PacketChat) {
						PacketChat packetChat = (PacketChat) packet;
						server.log(Level.INFO, packetChat.message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
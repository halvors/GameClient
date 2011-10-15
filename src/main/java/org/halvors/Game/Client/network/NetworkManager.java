package main.java.org.halvors.Game.Client.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import main.java.org.halvors.Game.Server.network.packet.Packet;

public class NetworkManager {
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	private final Socket socket;
	
	public NetworkManager(Socket socket) {
		this.socket = socket;
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packetQueue.add(packet);
		}
	}
	
	public void sendPacketWithoutQueuing(Packet packet) throws IOException {
//		Packet current = packetQueue.poll();
//		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//		current.writePacketData(out);
		
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		packet.writePacketData(out);
	}

	public Socket getSocket() {
		return socket;
	}
}

package main.java.org.halvors.Game.Client.network;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import main.java.org.halvors.Game.Client.network.packet.Packet;

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
	
//	public void sendPacke(Packet packet, Socket socket) throws IOException {
//		Packet current = packetQueue.poll();
//		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//		current.writePacketData(out);
//	}

	public Socket getSocket() {
		return socket;
	}
}

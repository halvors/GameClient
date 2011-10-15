package main.java.org.halvors.Game.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import main.java.org.halvors.Game.Client.packet.Packet;

public class NetworkManager {
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	private Socket socket = new Socket();
	
	public NetworkManager() {
		
	}
	
	public void connect(String host, int port) throws IOException {
		socket = new Socket(host, port);
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packetQueue.add(packet);
		}
	}
	
	public void sendPacke() throws IOException {
		Packet current = packetQueue.poll();
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		current.writePacketData(out);
	}
}

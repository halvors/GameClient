package main.java.org.halvors.Game.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import main.java.org.halvors.Game.Client.packet.Packet;

public class NetworkManager {
	private final Queue<Packet> packetqueue = new LinkedList<Packet>();
	private Socket socket = new Socket();
	
	public NetworkManager(String host, int port) throws IOException {
		connect(host, port);
	}
	
	public void connect(String host, int port) throws IOException {
		// Connect here.
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packetqueue.add
			(packet);
			
		}
	}
		public void sendPacke(Packet packet) throws IOException {
			
				
			
			Packet current = packetqueue.poll();
			current.writeString("test", socket.getOutputStream());
			
		}
				
			
	
}

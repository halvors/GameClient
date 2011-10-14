package main.java.org.halvors.Game.Client;


import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Client.packet.Packet;

public class NetworkManager {
	private final List<Packet>packets = Collections.synchronizedList(new ArrayList<Packet>());
	Socket socket = new Socket();
	
	
	public NetworkManager() {
		
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packets.add(packet);
			
		}
	}
}

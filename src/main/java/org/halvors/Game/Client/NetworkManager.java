package main.java.org.halvors.Game.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Client.packet.Packet;

public class NetworkManager {
	private final List<Packet>packets = Collections.synchronizedList(new ArrayList<Packet>());
	
	public NetworkManager() {
		
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packets.add(packet);
		}
	}
}

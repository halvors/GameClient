package main.java.org.halvors.Game.Client;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Client.packet.Packet;

public class NetworkManager {
	private final List<Packet>packets = Collections.synchronizedList(new ArrayList<Packet>());
	private final Socket socket = null;
	
	public NetworkManager(String host, int port {
		connect(host, port);
	}
	
	public void connect(String host, int port) {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packets.add(packet);
			
		}
	}
}

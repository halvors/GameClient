package main.java.org.halvors.Game.Server.network;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Client.network.packet.Packet;
import main.java.org.halvors.Game.Server.GameServer;

public class NetworkManager {
	private final Socket socket;
	private final List<Packet> packetQueue = Collections.synchronizedList(new ArrayList<Packet>());
	
	public NetworkManager(GameServer server, Socket socket) {
		this.socket = socket;
	}
	
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        }
    }
}

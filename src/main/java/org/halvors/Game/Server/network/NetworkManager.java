package main.java.org.halvors.Game.Server.network;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Server.network.packet.Packet;

public class NetworkManager {
	private final Socket socket;
	private final List<Packet> packetQueue = Collections.synchronizedList(new ArrayList<Packet>());
	private final Thread readThread;
	private final Thread writeThread;
	
	public NetworkManager(Socket socket) {
		this.socket = socket;
		this.readThread = new NetworkReaderThread(this);
        this.writeThread = new NetworkWriterThread(this);
        readThread.start();
        writeThread.start();
	}
	
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        }
    }

	public Socket getSocket() {
		return socket;
	}
}

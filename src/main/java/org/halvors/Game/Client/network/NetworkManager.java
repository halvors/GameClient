package org.halvors.Game.Client.network;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.Packet;

public class NetworkManager {
	private final Socket socket;
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	private final NetworkClientHandler networkClientHandler;
	private final Thread readerThread;
	private final Thread writerThread;
	
	public NetworkManager(Socket socket) {
		this.socket = socket;
		this.networkClientHandler = new NetworkClientHandler(Game.getInstance(), this);
		this.readerThread = new NetworkReaderThread(this);
		this.writerThread = new NetworkWriterThread(this);
		readerThread.start();
		writerThread.start();
	}
	
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        }
    }

	public Socket getSocket() {
		return socket;
	}
	
	public Queue<Packet> getPacketQueue() {
		return packetQueue;
	}

	public NetworkClientHandler getNetworkClientHandler() {
		return networkClientHandler;
	}
}

package org.halvors.Game.Client.network;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.Packet;

public class NetworkManager {
	private final Game client;
	private final Socket socket;
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	private final ClientHandler clientHandler;
	private final ReaderThread readerThread;
	private final WriterThread writerThread;
	
	public NetworkManager(Game client, Socket socket) {
		this.client = client;
		this.socket = socket;
		this.clientHandler = new ClientHandler(client, this);
		this.readerThread = new ReaderThread("Reader thread", this);
		this.writerThread = new WriterThread("Writer thread", this);
		readerThread.start();
		writerThread.start();
	}
	
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        }
    }
	
	public void disconnect() throws IOException {
		// TODO: Maybe do some more here.
		socket.close();
	}

	public Game getClient() {
		return client;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public Queue<Packet> getPacketQueue() {
		return packetQueue;
	}

	public ClientHandler getClientHandler() {
		return clientHandler;
	}
}

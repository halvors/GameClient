package main.java.org.halvors.Game.Server.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import main.java.org.halvors.Game.Server.GameServer;

public class NetworkListenThread extends Thread {
	private final GameServer server;
	private final ServerSocket serverSocket;
	private final Thread thread;
	
//	private final List<Socket> clients = Collections.synchronizedList(new ArrayList<Socket>());
	
	public NetworkListenThread(GameServer server, InetAddress address, int port) throws IOException {
		this.server = server;
		this.serverSocket = new ServerSocket(port, 0, address);
		this.thread = new NetworkAcceptThread(server, this);
		thread.start();
	}
	
	public void run() {
		while (!serverSocket.isClosed()) {
			
		}
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}

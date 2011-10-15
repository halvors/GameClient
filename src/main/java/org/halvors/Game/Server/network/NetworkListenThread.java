package main.java.org.halvors.Game.Server.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import main.java.org.halvors.Game.Server.GameServer;

public class NetworkListenThread extends Thread {
	private final GameServer server;
	private final ServerSocket serverSocket;
	
	private final List<Socket> clients = Collections.synchronizedList(new ArrayList<Socket>());
	
	public NetworkListenThread(GameServer server, InetAddress address, int port) throws IOException {
		this.server = server;
		this.serverSocket = new ServerSocket(port, 0, address);
	}
	
	public void run() {
		while (!serverSocket.isClosed()) {
			try {
				Socket socket = serverSocket.accept();
				
				if (socket != null && socket.isBound()) {
					server.log(Level.INFO, "Connection accepted from: " + socket.getRemoteSocketAddress().toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}

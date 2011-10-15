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
				
				// Remove old sockets.
				if (hasClient(socket)) {
					removeClient(socket);
				}
				
				if (socket != null && socket.isBound()) {
					// Add the socket to the clients list.
					addClient(socket);
					
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
	
	public boolean hasClient(Socket socket) {
		for (Socket s : clients) {
			if (socket.getInetAddress() == s.getInetAddress() && socket.getPort() == s.getPort()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addClient(Socket socket) {
		if (!clients.contains(socket)) {
			clients.add(socket);
		}
	}
	
	public void removeClient(Socket socket) {
		if (clients.contains(socket)) {
			clients.remove(socket);
		}
	}

	public List<Socket> getClients() {
		return clients;
	}
}

package main.java.org.halvors.Game.Server.network;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import main.java.org.halvors.Game.Server.GameServer;

public class NetworkAcceptThread extends Thread {
	private final GameServer server;
	private final NetworkListenThread networkListenThread;
	
	public NetworkAcceptThread(GameServer server, NetworkListenThread networkListenThread) {
		this.server = server;
		this.networkListenThread = networkListenThread;
	}
	
	public void run() {
		while (true) {
			try {
				Socket socket = networkListenThread.getServerSocket().accept();
				
				if (socket != null) {
					server.log(Level.INFO, "Connection accepted from: " + socket.getRemoteSocketAddress().toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package main.java.org.halvors.Game.Server.network;

import java.net.Socket;

public class NetworkWriterThread extends Thread {
	private final Socket socket;
	
	public NetworkWriterThread(NetworkManager networkManager) {
		this.socket = networkManager.getSocket();
	}
	
	public void run() {
		// TODO: Write packets to socket from queue here.
	}
}

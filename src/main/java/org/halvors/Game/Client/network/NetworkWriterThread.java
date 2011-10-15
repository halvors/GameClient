package main.java.org.halvors.Game.Client.network;

public class NetworkWriterThread extends Thread {
	private final NetworkManager networkManager;
	
	public NetworkWriterThread(NetworkManager networkManager) {
		this.networkManager = networkManager;
	}
	
	public void run() {
		// TODO: Write packets to socket from queue here.
	}
}

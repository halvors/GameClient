package main.java.org.halvors.Game.Client.network;

public class NetworkReaderThread extends Thread {
	private final NetworkManager networkManager;
	
	public NetworkReaderThread(NetworkManager networkManager) {
		this.networkManager = networkManager;
	}
	
	public void run() {
		// TODO: Read packets from socket here.
	}
}
